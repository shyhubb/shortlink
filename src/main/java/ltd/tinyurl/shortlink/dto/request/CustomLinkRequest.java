package ltd.tinyurl.shortlink.dto.request;

import lombok.Data;

@Data
public class CustomLinkRequest {
    private String originalLink;
    private String customShortCode;
}
