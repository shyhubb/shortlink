package ltd.tinyurl.shortlink.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.Data;
import ltd.tinyurl.shortlink.dto.request.ProfileRequest;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.UserResponse;
import ltd.tinyurl.shortlink.service.impl.UserServiceImpl;
import ltd.tinyurl.shortlink.webconstants.WebConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(WebConstants.USER_API_V1_PATH)
@Data
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @SuppressWarnings("unchecked")
    @GetMapping("/getprofile")
    public ResponseEntity<BaseResponse<UserResponse>> getProfile() {
        BaseResponse profile = userServiceImpl.getProfile();
        String message = profile.getMessage();
        if (message.equals(WebConstants.BASE_SUCCESS)) {
            return new ResponseEntity<BaseResponse<UserResponse>>(profile, HttpStatus.OK);
        }
        return new ResponseEntity<BaseResponse<UserResponse>>(profile, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/profile/update")
    public ResponseEntity<BaseResponse> updateProfile(@RequestBody ProfileRequest profileRequest) {
        BaseResponse update = userServiceImpl.updateProfile(profileRequest);
        String message = update.getMessage();
        if (message.equals(WebConstants.BASE_SUCCESS))
            return new ResponseEntity<BaseResponse>(update, HttpStatus.OK);
        return new ResponseEntity<BaseResponse>(update, HttpStatus.BAD_REQUEST);
    }

}
