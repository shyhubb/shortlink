package ltd.tinyurl.shortlink.service;

import ltd.tinyurl.shortlink.dto.request.ProfileRequest;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.ManagerUserResponse;
import ltd.tinyurl.shortlink.dto.response.UserResponse;

public interface UserService {
    public BaseResponse<UserResponse> getProfile();

    public BaseResponse updateProfile(ProfileRequest profileRequest);

    public BaseResponse<ManagerUserResponse> managerUsers();

    public BaseResponse deleteById(Long id);
}
