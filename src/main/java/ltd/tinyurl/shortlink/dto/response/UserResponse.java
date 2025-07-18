package ltd.tinyurl.shortlink.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String account;
    private String name;
    private String email;
    private Double balance;
    private String role;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String bankName;
    private String bankAdress;

}
