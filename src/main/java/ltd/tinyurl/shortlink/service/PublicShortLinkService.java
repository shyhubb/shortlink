package ltd.tinyurl.shortlink.service;

import ltd.tinyurl.shortlink.dto.request.LinkPublicRequest;

public interface PublicShortLinkService {
    public String GenerateShortLink(LinkPublicRequest linkPublicRequest);

    public String getLink(String shortCode);
}
