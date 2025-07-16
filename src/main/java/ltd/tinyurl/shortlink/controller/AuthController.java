package ltd.tinyurl.shortlink.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ltd.tinyurl.shortlink.dto.request.LoginRequest;
import ltd.tinyurl.shortlink.dto.request.UserRequest;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.LoginResponse;
import ltd.tinyurl.shortlink.service.impl.AuthServiceImpl;
import ltd.tinyurl.shortlink.webconstants.WebConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(WebConstants.AUTH_API_V1_PATH)
public class AuthController {
    private final AuthServiceImpl authServiceImpl;

    public AuthController(AuthServiceImpl authServiceImpl) {
        this.authServiceImpl = authServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> register(@RequestBody UserRequest userRequest) {
        BaseResponse registerMessage = authServiceImpl.register(userRequest);
        String message = registerMessage.getMessage();

        if (message.equals(WebConstants.BASE_SUCCESS))
            return new ResponseEntity<>(registerMessage, HttpStatus.CREATED);
        return new ResponseEntity<>(registerMessage, HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("unchecked")
    @PostMapping("login")
    public ResponseEntity<BaseResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        BaseResponse login = authServiceImpl.login(loginRequest);
        String message = login.getMessage();

        if (message.equals(WebConstants.BASE_SUCCESS)) {
            return new ResponseEntity<BaseResponse<LoginResponse>>(login, HttpStatus.OK);
        }
        return new ResponseEntity<BaseResponse<LoginResponse>>(login, HttpStatus.BAD_REQUEST);
    }

}
