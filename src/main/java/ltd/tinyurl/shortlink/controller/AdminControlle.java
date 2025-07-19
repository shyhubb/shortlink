package ltd.tinyurl.shortlink.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.Data;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.service.impl.UserServiceImpl;
import ltd.tinyurl.shortlink.webconstants.WebConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@Data
@RequestMapping(WebConstants.ADMIN_API_V1_PATH)
public class AdminControlle {
    private final UserServiceImpl userServiceImpl;

    @GetMapping("user/manager")
    public ResponseEntity<BaseResponse> managerUser() {
        BaseResponse managerUser = userServiceImpl.managerUsers();
        String message = managerUser.getMessage();
        if (message.equals(WebConstants.BASE_SUCCESS))
            return new ResponseEntity<BaseResponse>(managerUser, HttpStatus.OK);
        return new ResponseEntity<BaseResponse>(managerUser, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<BaseResponse<String>> deleteUserById(@PathVariable Long id) {
        Long userId = id;

        BaseResponse<String> deleteUser = userServiceImpl.deleteById(userId);
        String message = deleteUser.getMessage();
        if (message.equals(WebConstants.BASE_SUCCESS))
            return new ResponseEntity<BaseResponse<String>>(deleteUser, HttpStatus.OK);
        if (message.equals(WebConstants.ERROR_DELETE_THIS_ADMIN))
            return new ResponseEntity<BaseResponse<String>>(deleteUser, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<BaseResponse<String>>(deleteUser, HttpStatus.NOT_FOUND);

    }

}
