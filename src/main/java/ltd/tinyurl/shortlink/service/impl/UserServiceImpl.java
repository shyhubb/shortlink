package ltd.tinyurl.shortlink.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import lombok.Data;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.UserResponse;
import ltd.tinyurl.shortlink.entity.User;
import ltd.tinyurl.shortlink.repository.UserRepository;
import ltd.tinyurl.shortlink.service.UserService;
import ltd.tinyurl.shortlink.webconstants.WebConstants;

@Service
@Data
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final WalletServiceImpl walletServiceImpl;

    private final CurrentUserDetails currentUserDetails;

    @Override
    public BaseResponse<UserResponse> getProfile() {
        Long currentUserId = currentUserDetails.getUserDetails().getId();
        User user = new User();

        Optional<User> userOptional = userRepository.findById(currentUserId);
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }
        Double userBalance = walletServiceImpl.getBalance();

        UserResponse userResponse = new UserResponse();
        userResponse.setAccount(user.getAccount());
        userResponse.setBalance(userBalance);
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setBankAdress(user.getBankAdress());
        userResponse.setCreateAt(user.getCreateAt());
        userResponse.setUpdateAt(user.getUpdateAt());
        userResponse.setBankName(user.getBankName());

        return new BaseResponse<UserResponse>(WebConstants.BASE_SUCCESS, userResponse);
    }
}