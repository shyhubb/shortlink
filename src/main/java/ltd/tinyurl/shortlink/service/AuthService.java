package ltd.tinyurl.shortlink.service;

import ltd.tinyurl.shortlink.dto.request.LoginRequest;
import ltd.tinyurl.shortlink.dto.request.UserRequest;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.LoginResponse;

public interface AuthService {
    @SuppressWarnings("rawtypes")
    public BaseResponse register(UserRequest UserRequest);

    public BaseResponse<LoginResponse> login(LoginRequest loginRequest);
}
