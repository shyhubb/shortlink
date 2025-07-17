package ltd.tinyurl.shortlink.service;

import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.UserResponse;

public interface UserService {
    public BaseResponse<UserResponse> getProfile();
}
