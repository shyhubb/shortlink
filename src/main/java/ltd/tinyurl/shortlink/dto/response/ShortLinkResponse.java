package ltd.tinyurl.shortlink.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ShortLinkResponse {
    private String shortLink;
    private LocalDateTime createAt;
    private Long id;
    private String originalLink;
    private LocalDateTime updateAt;

}
