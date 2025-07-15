package ltd.tinyurl.shortlink.service.impl;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import ltd.tinyurl.shortlink.dto.request.LinkPublicRequest;
import ltd.tinyurl.shortlink.entity.Link;
import ltd.tinyurl.shortlink.service.PublicShortLinkService;
import ltd.tinyurl.shortlink.webconstants.WebConstants;

@Service
public class PublicShortLinkServiceImpl implements PublicShortLinkService {

    private final ShortlinkServiceImpl shortlinkServiceImpl;

    public PublicShortLinkServiceImpl(ShortlinkServiceImpl shortlinkServiceImpl) {
        this.shortlinkServiceImpl = shortlinkServiceImpl;
    }

    @Override
    public String GenerateShortLink(LinkPublicRequest linkPublicRequest) {
        String originLink = linkPublicRequest.getOriginalLink();
        StringBuilder shortLink = new StringBuilder();
        String shortCode = shortlinkServiceImpl.GenerateUrl();
        shortLink.append(WebConstants.BASE_SHORT_URL_DOMAIN + shortCode);
        LocalDateTime createAt = LocalDateTime.now();
        Link link = new Link();
        link.setCreateAt(createAt);
        link.setShortCode(shortCode);
        link.setOriginalLink(originLink);
        shortlinkServiceImpl.createShortLink(link);

        return shortLink.toString();
    }

    @Override
    public String getLink(String shortCode) {
        return shortlinkServiceImpl.getLink(shortCode);
    }

}
