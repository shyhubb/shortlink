package ltd.tinyurl.shortlink.service;

import java.util.List;

import ltd.tinyurl.shortlink.dto.request.CustomLinkRequest;
import ltd.tinyurl.shortlink.dto.request.LinkRequest;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.CreateLinkResponse;
import ltd.tinyurl.shortlink.dto.response.ShortLinkResponse;
import ltd.tinyurl.shortlink.entity.Link;

public interface ShortlinkService {

    public BaseResponse<CreateLinkResponse> generateShortLink(LinkRequest linkPublicRequest,
            String clientIpAddress);

    public String randomGenerateUrl();

    public boolean existsByShortCode(String shortCode); // ham check key url da ton tai chua

    public String generateUrl();

    public void createShortLink(Link link);

    public String getLink(String shortCode);

    public int countByClientIp(String clientIp);

    public BaseResponse<CreateLinkResponse> generateCustomShortLink(CustomLinkRequest customLinkRequest,
            String clientIpAddress);

    public BaseResponse<List<ShortLinkResponse>> findAllShortLink();
}
