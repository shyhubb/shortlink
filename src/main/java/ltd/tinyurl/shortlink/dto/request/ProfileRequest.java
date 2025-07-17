package ltd.tinyurl.shortlink.dto.request;

import lombok.Data;

@Data
public class ProfileRequest {
    private String name;
    private String email;
    private String bankName;
    private String bankAdress;
}
