package ltd.tinyurl.shortlink.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import ltd.tinyurl.shortlink.dto.request.LinkRequest;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.CreateLinkResponse;
import ltd.tinyurl.shortlink.service.impl.PublicShortLinkServiceImpl;
import ltd.tinyurl.shortlink.webconstants.WebConstants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(WebConstants.PUBLIC_SHORT_LINK_API_V1_PATH)
public class PublicShortlinkController {

    private final PublicShortLinkServiceImpl publicShortLinkServiceImpl;

    public PublicShortlinkController(PublicShortLinkServiceImpl publicShortLinkServiceImpl) {
        this.publicShortLinkServiceImpl = publicShortLinkServiceImpl;
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<CreateLinkResponse>> createShortLink(
            @RequestBody LinkRequest linkPublicRequest,
            HttpServletRequest request) {
        String clientIp = getClientIpAddress(request);
        BaseResponse<CreateLinkResponse> baseResponse = publicShortLinkServiceImpl.generateShortLink(linkPublicRequest,
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

    private String getClientIpAddress(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null || xfHeader.isEmpty() || "unknown".equalsIgnoreCase(xfHeader)) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}
