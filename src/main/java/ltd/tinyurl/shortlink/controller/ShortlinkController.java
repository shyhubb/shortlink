package ltd.tinyurl.shortlink.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import ltd.tinyurl.shortlink.dto.request.CustomLinkRequest;
import ltd.tinyurl.shortlink.dto.request.LinkRequest;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.CreateLinkResponse;
import ltd.tinyurl.shortlink.dto.response.ShortLinkResponse;
import ltd.tinyurl.shortlink.service.impl.ShortlinkServiceImpl;
import ltd.tinyurl.shortlink.webconstants.WebConstants;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(WebConstants.PRIVATE_SHORT_LINK_API_V1_PATH)
public class ShortlinkController {

    private final ShortlinkServiceImpl shortlinkServiceImpl;

    public ShortlinkController(ShortlinkServiceImpl shortlinkServiceImpl) {
        this.shortlinkServiceImpl = shortlinkServiceImpl;
    }

    @PostMapping("/create/random")
    public ResponseEntity<BaseResponse<CreateLinkResponse>> createRandomShortLink(
            @RequestBody LinkRequest linkPublicRequest,
            HttpServletRequest request) {
        String clientIp = getClientIpAddress(request);
        BaseResponse<CreateLinkResponse> baseResponse = shortlinkServiceImpl.generateShortLink(linkPublicRequest,
                clientIp);
        if (baseResponse.getMessage().equals(WebConstants.BASE_SUCCESS)) {
            return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
        } else {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            if (baseResponse.getMessage().equals(WebConstants.CREATE_LINK_LIMIT)) {
                status = HttpStatus.TOO_MANY_REQUESTS;
            }
            return new ResponseEntity<>(baseResponse, status);
        }
    }

    @PostMapping("/create/custom")
    public ResponseEntity<BaseResponse<CreateLinkResponse>> createCustomShortLink(
            @RequestBody CustomLinkRequest customLinkRequest,
            HttpServletRequest request) {
        String clientIp = getClientIpAddress(request);
        BaseResponse<CreateLinkResponse> baseResponse = shortlinkServiceImpl.generateCustomShortLink(customLinkRequest,
                clientIp);
        if (baseResponse.getMessage().equals(WebConstants.BASE_SUCCESS)) {
            return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
        } else {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            if (baseResponse.getMessage().equals(WebConstants.SHORT_CODE_EXISTS_ERROR)) {
                return new ResponseEntity<>(baseResponse, status);
            }
            return new ResponseEntity<>(baseResponse, status);
        }
    }

    @GetMapping("/findall")
    @SuppressWarnings({ "unused", "unchecked" })
    private ResponseEntity<BaseResponse<List<ShortLinkResponse>>> findAllShortLink() {
        BaseResponse shortLinkResponse = shortlinkServiceImpl.findAllShortLink();

        if (shortLinkResponse.getMessage().equals(WebConstants.BASE_SUCCESS))
            return new ResponseEntity<BaseResponse<List<ShortLinkResponse>>>(shortLinkResponse, HttpStatus.OK);
        return new ResponseEntity<BaseResponse<List<ShortLinkResponse>>>(shortLinkResponse, HttpStatus.BAD_REQUEST);
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null || xfHeader.isEmpty() || "unknown".equalsIgnoreCase(xfHeader)) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}