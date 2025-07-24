package ltd.tinyurl.shortlink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import ltd.tinyurl.shortlink.entity.Link;
import ltd.tinyurl.shortlink.repository.LinkRepository;
import ltd.tinyurl.shortlink.webconstants.WebConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;

import java.util.Optional;
import java.util.UUID;

@Controller
@Data
@RequestMapping("/")
public class GetLinkController {

    private final LinkRepository linkRepository;

    private static final String SESSION_SHORT_CODE = "currentShortCode";
    private static final String SESSION_FLOW_TOKEN = "currentFlowToken";
    private static final String SESSION_FLOW_STEP = "currentFlowStep";

    // Bỏ constructor tùy chỉnh bạn đã viết trước đó!
    // @RequiredArgsConstructor sẽ tạo constructor cho linkRepository và
    // geoIpDatabaseReader

    @GetMapping("favicon.ico")
    public ResponseEntity<Void> favicon() {
        System.out.println("DEBUG: Favicon request received. Ignoring.");
        return ResponseEntity.noContent().build();
    }

    // --- Hàm trợ giúp để lấy IP của client ---
    private String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        // Đối với localhost, getRemoteAddr() sẽ trả về 0:0:0:0:0:0:0:1 hoặc 127.0.0.1
        if ("0:0:0:0:0:0:0:1".equals(ipAddress)) {
            ipAddress = "127.0.0.1";
        }
        // Đôi khi có thể có nhiều IP, lấy cái đầu tiên
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0].trim();
        }
        return ipAddress;
    }

    // 1. Endpoint ban đầu khi click vào short link
    @GetMapping("{shortCode}")
    public String redirectToCountdown1(@PathVariable String shortCode, Model model, HttpSession session,
            HttpServletRequest request) {
        String shortLink = WebConstants.BASE_SHORT_URL_DOMAIN + shortCode;
        Optional<Link> linkOptional = linkRepository.findByShortLink(shortLink);

        if (!linkOptional.isPresent()) {
            model.addAttribute("frontendBaseUrl", WebConstants.FRONTEND_BASE_URL);
            return "error/404";
        }
        Link link = linkOptional.get();

        if (link.getUser() == null)
            return "redirect:" + link.getOriginalLink();

        // --- Các bước tiếp theo chỉ dành cho link có User và cần qua luồng đếm ngược
        // ---
        session.removeAttribute(SESSION_SHORT_CODE);
        session.removeAttribute(SESSION_FLOW_TOKEN);
        session.removeAttribute(SESSION_FLOW_STEP);

        String flowToken = UUID.randomUUID().toString();

        session.setAttribute(SESSION_SHORT_CODE, shortCode);
        session.setAttribute(SESSION_FLOW_TOKEN, flowToken);
        session.setAttribute(SESSION_FLOW_STEP, 1);

        model.addAttribute("shortCode", shortCode);
        model.addAttribute("flowToken", flowToken);
        model.addAttribute("frontendBaseUrl", WebConstants.FRONTEND_BASE_URL);

        System.out.println("DEBUG: Entering step 1 for shortCode: " + shortCode + ", token: " + flowToken);
        return "countdown1";
    }

    private boolean validateFlow(HttpSession session, String shortCode, String flowToken, int expectedStep) {
        String sessionShortCode = (String) session.getAttribute(SESSION_SHORT_CODE);
        String sessionFlowToken = (String) session.getAttribute(SESSION_FLOW_TOKEN);
        Integer sessionFlowStep = (Integer) session.getAttribute(SESSION_FLOW_STEP);

        System.out.println("--- Validation Check for Step " + expectedStep + " ---");
        System.out.println("Request ShortCode: " + shortCode + ", Session ShortCode: " + sessionShortCode);
        System.out.println("Request FlowToken: " + flowToken + ", Session FlowToken: " + sessionFlowToken);
        System.out.println("Expected Step: " + expectedStep + ", Session Current Step: " + sessionFlowStep);

        boolean isShortCodeMatch = shortCode != null && shortCode.equals(sessionShortCode);
        boolean isFlowTokenMatch = flowToken != null && flowToken.equals(sessionFlowToken);
        boolean isStepMatch = sessionFlowStep != null && sessionFlowStep == expectedStep;

        System.out.println("ShortCode Match: " + isShortCodeMatch);
        System.out.println("FlowToken Match: " + isFlowTokenMatch);
        System.out.println("Step Match: " + isStepMatch);
        System.out.println("Validation Result: " + (isShortCodeMatch && isFlowTokenMatch && isStepMatch));
        System.out.println("------------------------------------");

        return isShortCodeMatch && isFlowTokenMatch && isStepMatch;
    }

    // 2. Endpoint cho trang đếm ngược thứ hai
    @GetMapping("countdown2")
    public String showCountdown2Page(@RequestParam String shortCode, @RequestParam String flowToken, Model model,
            HttpSession session, HttpServletRequest request) {
        if (!validateFlow(session, shortCode, flowToken, 1)) {
            System.out.println("DEBUG: Validation FAILED for countdown2. Redirecting to start.");
            return "redirect:/" + shortCode;
        }

        session.setAttribute(SESSION_FLOW_STEP, 2);
        System.out.println("DEBUG: Validation SUCCESS for countdown2. Moving to step 2 for shortCode: " + shortCode
                + ", token: " + flowToken);

        model.addAttribute("shortCode", shortCode);
        model.addAttribute("flowToken", flowToken);
        model.addAttribute("frontendBaseUrl", WebConstants.FRONTEND_BASE_URL);
        return "countdown2";
    }

    // 3. Endpoint cho trang lấy link cuối cùng
    @GetMapping("getLinkPage")
    public String showGetLinkPage(@RequestParam String shortCode, @RequestParam String flowToken, Model model,
            HttpSession session, HttpServletRequest request) {
        if (!validateFlow(session, shortCode, flowToken, 2)) {
            System.out.println("DEBUG: Validation FAILED for getLinkPage. Redirecting to start.");
            return "redirect:/" + shortCode;
        }

        String clientIp = getClientIp(request);
        System.out.println("DEBUG: GetLinkPage - Client IP: " + clientIp);

        session.setAttribute(SESSION_FLOW_STEP, 3);
        System.out.println("DEBUG: Validation SUCCESS for getLinkPage. Moving to step 3 for shortCode: " + shortCode
                + ", token: " + flowToken);

        model.addAttribute("shortCode", shortCode);
        model.addAttribute("flowToken", flowToken);
        model.addAttribute("frontendBaseUrl", WebConstants.FRONTEND_BASE_URL);
        return "getLinkPage";
    }

    // 4. Endpoint thực hiện chuyển hướng cuối cùng
    @GetMapping("final-redirect")
    public String finalRedirect(@RequestParam String shortCode, @RequestParam String flowToken, HttpSession session,
            Model model, HttpServletRequest request) {
        if (!validateFlow(session, shortCode, flowToken, 3)) {
            System.out.println("DEBUG: Validation FAILED for final-redirect. Redirecting to start.");
            return "redirect:/" + shortCode;
        }

        session.removeAttribute(SESSION_SHORT_CODE);
        session.removeAttribute(SESSION_FLOW_TOKEN);
        session.removeAttribute(SESSION_FLOW_STEP);

        String shortLink = WebConstants.BASE_SHORT_URL_DOMAIN + shortCode;
        Optional<Link> linkOptional = linkRepository.findByShortLink(shortLink);

        if (!linkOptional.isPresent()) {
            model.addAttribute("frontendBaseUrl", WebConstants.FRONTEND_BASE_URL);
            return "error/404";
        }

        String clientIp = getClientIp(request);
        System.out.println("DEBUG: Final Redirect - Client IP: " + clientIp);

        return "redirect:" + linkOptional.get().getOriginalLink();
    }
}