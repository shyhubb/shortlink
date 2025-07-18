package ltd.tinyurl.shortlink.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class ManagerUserResponse {
    private List<UserResponse> users;
    private int count;

}
