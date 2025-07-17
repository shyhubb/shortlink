package ltd.tinyurl.shortlink.service.impl;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import lombok.Data;
import ltd.tinyurl.shortlink.dto.request.LinkRequest;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.CreateLinkResponse;
import ltd.tinyurl.shortlink.entity.Link;
import ltd.tinyurl.shortlink.repository.LinkRepository;
import ltd.tinyurl.shortlink.service.PublicShortLinkService;
import ltd.tinyurl.shortlink.webconstants.WebConstants;

@Service
@Data
public class PublicShortLinkServiceImpl implements PublicShortLinkService {

    private final ShortlinkServiceImpl shortlinkServiceImpl;
    private final LinkRepository linkRepository;

    @Override
    public BaseResponse<CreateLinkResponse> generateShortLink(LinkRequest linkRequest, String clientIp) {
        LocalDateTime time = LocalDateTime.now().minusHours(WebConstants.TIME_CREATE_LINK_LIMIT);
        int countLink = linkRepository.countByClientIpAndUpdateAtAfter(clientIp,
                time);
        if (countLink > WebConstants.CREATE_LINK_LIMIT) {
            return new BaseResponse<CreateLinkResponse>(WebConstants.LIMIT_CREATE_LINK_ERROR, null);
        }

        String originLink = linkRequest.getOriginalLink();
        StringBuilder shortLink = new StringBuilder();
        String shortCode = shortlinkServiceImpl.generateUrl();
        shortLink.append(WebConstants.BASE_SHORT_URL_DOMAIN + shortCode);
        LocalDateTime createAt = LocalDateTime.now();
        Link link = new Link();
        link.setCreateAt(createAt);
        link.setUpdateAt(createAt);
        link.setShortCode(shortCode);
        link.setOriginalLink(originLink);
        link.setClientIp(clientIp);
        link.setShortLink(shortLink.toString());
        shortlinkServiceImpl.createShortLink(link);

        return new BaseResponse<CreateLinkResponse>(WebConstants.BASE_SUCCESS,
                new CreateLinkResponse(shortLink.toString()));
    }

    @Override
    public String getLink(String shortCode) {
        return shortlinkServiceImpl.getLink(shortCode);
    }

    @Override
    public int countByClientIp(String clientIp) {
        return shortlinkServiceImpl.countByClientIp(clientIp);
    }

}
