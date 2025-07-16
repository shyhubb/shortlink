package ltd.tinyurl.shortlink.service;

import ltd.tinyurl.shortlink.dto.request.LinkRequest;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.CreateLinkResponse;

public interface PublicShortLinkService {

    public BaseResponse<CreateLinkResponse> generateShortLink(LinkRequest linkRequest,
            String clientIpAddress);

    public String getLink(String shortCode);

    public int countByClientIp(String clientIp);
}
