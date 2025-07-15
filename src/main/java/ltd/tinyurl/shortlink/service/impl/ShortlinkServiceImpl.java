package ltd.tinyurl.shortlink.service.impl;

import java.security.SecureRandom;
import org.springframework.stereotype.Service;

import ltd.tinyurl.shortlink.entity.Link;
import ltd.tinyurl.shortlink.repository.LinkRepository;
import ltd.tinyurl.shortlink.service.ShortlinkService;
import ltd.tinyurl.shortlink.webconstants.WebConstants;

@Service
public class ShortlinkServiceImpl implements ShortlinkService {

    private final LinkRepository linkRepository;

    private final SecureRandom random = new SecureRandom();

    public ShortlinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
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
    public String GenerateUrl() {
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
            return "hehe";
        return linkRepository.findByShortCode(shortCode).getOriginalLink();
    }

}
