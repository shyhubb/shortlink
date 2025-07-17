package ltd.tinyurl.shortlink.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private String account;
    private String name;
    private String email;
    private Double balance;
}
