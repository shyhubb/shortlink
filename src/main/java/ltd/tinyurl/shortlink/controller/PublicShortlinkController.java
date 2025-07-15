package ltd.tinyurl.shortlink.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ltd.tinyurl.shortlink.dto.request.LinkPublicRequest;
import ltd.tinyurl.shortlink.service.impl.PublicShortLinkServiceImpl;
import ltd.tinyurl.shortlink.webconstants.WebConstants;
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
    public String createShortLink(@RequestBody LinkPublicRequest linkPublicRequest) {
        return publicShortLinkServiceImpl.GenerateShortLink(linkPublicRequest);
    }

}
