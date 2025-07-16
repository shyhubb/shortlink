package ltd.tinyurl.shortlink.controller;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ltd.tinyurl.shortlink.service.impl.PublicShortLinkServiceImpl;
import ltd.tinyurl.shortlink.webconstants.WebConstants;

@RestController
@RequestMapping("/")
public class GetLinkController {
    private final PublicShortLinkServiceImpl publicShortLinkServiceImpl;

    public GetLinkController(PublicShortLinkServiceImpl publicShortLinkServiceImpl) {
        this.publicShortLinkServiceImpl = publicShortLinkServiceImpl;
    }

    @GetMapping("{shortCode}")
    public ResponseEntity<Void> getLink(@PathVariable String shortCode) throws URISyntaxException {
        String message = publicShortLinkServiceImpl.getLink(shortCode);
        if (message.equals(WebConstants.SHORT_LINK_NOT_FOUND_MESSAGE)) {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        URI url = new URI(message);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(url);
        return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
    }
}
