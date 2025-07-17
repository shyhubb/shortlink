package ltd.tinyurl.shortlink.service.impl;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Data;
import ltd.tinyurl.shortlink.dto.request.CustomLinkRequest;
import ltd.tinyurl.shortlink.dto.request.LinkRequest;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.CreateLinkResponse;
import ltd.tinyurl.shortlink.dto.response.ShortLinkResponse;
import ltd.tinyurl.shortlink.entity.Link;
import ltd.tinyurl.shortlink.entity.User;
import ltd.tinyurl.shortlink.repository.LinkRepository;
import ltd.tinyurl.shortlink.service.ShortlinkService;
import ltd.tinyurl.shortlink.webconstants.WebConstants;

@Service
@Data
public class ShortlinkServiceImpl implements ShortlinkService {

    private final LinkRepository linkRepository;

    private final SecureRandom random = new SecureRandom();

    private final CurrentUserDetails currentUserDetails;

    @Override
    public BaseResponse<CreateLinkResponse> generateShortLink(LinkRequest linkPublicRequest, String clientIp) {
        LocalDateTime time = LocalDateTime.now().minusHours(WebConstants.TIME_CREATE_LINK_LIMIT);
        int countLink = linkRepository.countByClientIpAndUpdateAtAfter(clientIp,
                time);
        if (countLink > WebConstants.CREATE_LINK_LIMIT) {
            return new BaseResponse<CreateLinkResponse>(WebConstants.LIMIT_CREATE_LINK_ERROR, null);
        }

        String originLink = linkPublicRequest.getOriginalLink();
        StringBuilder shortLink = new StringBuilder();
        String shortCode = generateUrl();
        shortLink.append(WebConstants.BASE_SHORT_URL_DOMAIN + shortCode);
        LocalDateTime createAt = LocalDateTime.now();
        Link link = new Link();
        User user = currentUserDetails.getUserDetails();
        link.setUpdateAt(createAt);
        link.setCreateAt(createAt);
        link.setShortCode(shortCode);
        link.setOriginalLink(originLink);
        link.setClientIp(clientIp);
        link.setUser(user);
        link.setShortLink(shortLink.toString());
        createShortLink(link);

        return new BaseResponse<CreateLinkResponse>(WebConstants.BASE_SUCCESS,
                new CreateLinkResponse(shortLink.toString()));
    }

    @Override
    public BaseResponse<CreateLinkResponse> generateCustomShortLink(CustomLinkRequest customLinkRequest,
            String clientIpAddress) {

        String customShortCode = customLinkRequest.getCustomShortCode();

        if (!customShortCode.matches("^[a-zA-Z0-9]+$")) {
            return new BaseResponse<>(WebConstants.SHORT_CODE_INVALID_CHARACTERS_ERROR, null);
        }

        if (customShortCode.length() < WebConstants.LENGTH_KEY_URL) {
            return new BaseResponse<CreateLinkResponse>(WebConstants.SHORT_CODE_LENGTH_ERROR, null);
        }
        LocalDateTime time = LocalDateTime.now().minusHours(WebConstants.TIME_CREATE_LINK_LIMIT);
        int countLink = linkRepository.countByClientIpAndUpdateAtAfter(clientIpAddress, time);

        if (countLink > WebConstants.CREATE_LINK_LIMIT) {
            return new BaseResponse<CreateLinkResponse>(WebConstants.LIMIT_CREATE_LINK_ERROR, null);
        }

        if (existsByShortCode(customShortCode)) {
            return new BaseResponse<CreateLinkResponse>(WebConstants.SHORT_CODE_EXISTS_ERROR, null);
        }
        String originLink = customLinkRequest.getOriginalLink();
        StringBuilder shortLink = new StringBuilder();
        shortLink.append(WebConstants.BASE_SHORT_URL_DOMAIN + customShortCode);
        LocalDateTime createAt = LocalDateTime.now();
        Link link = new Link();
        User user = currentUserDetails.getUserDetails();
        link.setCreateAt(createAt);
        link.setUpdateAt(createAt);
        link.setShortCode(customShortCode);
        link.setOriginalLink(originLink);
        link.setClientIp(clientIpAddress);
        link.setUser(user);
        link.setShortLink(shortLink.toString());
        createShortLink(link);

        return new BaseResponse<CreateLinkResponse>(WebConstants.BASE_SUCCESS,
                new CreateLinkResponse(shortLink.toString()));
    }

    @Override
    public String randomGenerateUrl() {
        StringBuilder url = new StringBuilder();
        for (int i = 0; i < WebConstants.LENGTH_KEY_URL; i++) {
            int randomIndex = random.nextInt(WebConstants.URL_ALPHABET.length());
            url.append(WebConstants.URL_ALPHABET.charAt(randomIndex));
        }
        return url.toString();
    }

    @Override
    public String generateUrl() {
        String shortKey = "";
        int attempts = 0;
        do {
            shortKey = randomGenerateUrl();
            attempts++;
            if (attempts > WebConstants.MAX_ATTEMPTS_GENERATE_KEY) {
                throw new RuntimeException(
                        "Failed to generate a unique short URL key after " + WebConstants.MAX_ATTEMPTS_GENERATE_KEY
                                + " attempts.");
            }
        } while (existsByShortCode(shortKey));

        return shortKey;
    }

    @Override
    public boolean existsByShortCode(String shortCode) {
        return linkRepository.existsByShortCode(shortCode);
    }

    @Override
    public void createShortLink(Link link) {
        linkRepository.save(link);
    }

    @Override
    public String getLink(String shortCode) {
        if (!existsByShortCode(shortCode))
            return WebConstants.SHORT_LINK_NOT_FOUND_MESSAGE;
        return linkRepository.findByShortCode(shortCode).get().getOriginalLink();
    }

    @Override
    public int countByClientIp(String clientIp) {
        return linkRepository.countByClientIp(clientIp);
    }

    @Override
    public BaseResponse<List<ShortLinkResponse>> findAllShortLink() {
        List<Link> links = linkRepository.findByUser(currentUserDetails.getUserDetails());
        if (links.isEmpty())
            return new BaseResponse<>(WebConstants.DONT_HAVE_SHORTLINK_IN_SYSTEM, null);
        List<ShortLinkResponse> shortlinks = new ArrayList<>();
        for (Link link : links) {
            ShortLinkResponse linkResponse = new ShortLinkResponse();
            linkResponse.setCreateAt(link.getCreateAt());
            linkResponse.setId(link.getId());
            linkResponse.setOriginalLink(link.getOriginalLink());
            linkResponse.setUpdateAt(link.getUpdateAt());
            linkResponse.setShortLink(link.getShortLink());

            shortlinks.add(linkResponse);
        }

        return new BaseResponse<>(WebConstants.BASE_SUCCESS, shortlinks);
    }

}
