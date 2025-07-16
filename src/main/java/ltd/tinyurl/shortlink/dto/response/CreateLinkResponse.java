package ltd.tinyurl.shortlink.dto.response;

import lombok.Data;

@Data
public class CreateLinkResponse {
    private String shortLink;

    public CreateLinkResponse(String shortLink) {
        this.shortLink = shortLink;
    }

}
