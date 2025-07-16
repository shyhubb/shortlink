package ltd.tinyurl.shortlink.service;

import ltd.tinyurl.shortlink.dto.request.LinkPublicRequest;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.CreateLinkResponse;

public interface PublicShortLinkService {

    public BaseResponse<CreateLinkResponse> generateShortLink(LinkPublicRequest linkPublicRequest,
            String clientIpAddress);

    public String getLink(String shortCode);

    public int countByClientIp(String clientIp);
}
