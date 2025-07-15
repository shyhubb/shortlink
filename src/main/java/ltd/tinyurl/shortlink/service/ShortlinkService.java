package ltd.tinyurl.shortlink.service;

import ltd.tinyurl.shortlink.entity.Link;

public interface ShortlinkService {
    public String randomGenerateUrl();

    public boolean existsByShortCode(String shortCode); // ham check key url da ton tai chua

    public String GenerateUrl();

    public void createShortLink(Link link);

    public String getLink(String shortCode);
}
