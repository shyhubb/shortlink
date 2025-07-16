package ltd.tinyurl.shortlink.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String account;
    private String password;
}
