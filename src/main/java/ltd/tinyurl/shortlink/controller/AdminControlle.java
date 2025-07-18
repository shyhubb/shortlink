package ltd.tinyurl.shortlink.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.Data;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.service.impl.UserServiceImpl;
import ltd.tinyurl.shortlink.webconstants.WebConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@Data
@RequestMapping(WebConstants.ADMIN_API_V1_PATH)
public class AdminControlle {
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/managerusers")
    public ResponseEntity<BaseResponse> managerUser() {
        BaseResponse managerUser = userServiceImpl.managerUsers();
        String message = managerUser.getMessage();
        if (message.equals(WebConstants.BASE_SUCCESS))
            return new ResponseEntity<BaseResponse>(managerUser, HttpStatus.OK);
        return new ResponseEntity<BaseResponse>(managerUser, HttpStatus.NO_CONTENT);
    }

}
