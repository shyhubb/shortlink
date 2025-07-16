package ltd.tinyurl.shortlink.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private String account;
    private String password;
    private String confirmPassword;
}
