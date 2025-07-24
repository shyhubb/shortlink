package ltd.tinyurl.shortlink.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.Data;
import ltd.tinyurl.shortlink.dto.request.ChangePasswordRequest;
import ltd.tinyurl.shortlink.dto.request.ProfileRequest;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.ManagerUserResponse;
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

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public BaseResponse<UserResponse> getProfile() {
        Long currentUserId = currentUserDetails.getUserDetails().getId();
        User user = new User();

        Optional<User> userOptional = userRepository.findById(currentUserId);
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }

        UserResponse userResponse = new UserResponse();
        userResponse.setAccount(user.getAccount());
        userResponse.setBalance(user.getWallet().getBalance());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setBankAdress(user.getBankAdress());
        userResponse.setCreateAt(user.getCreateAt());
        userResponse.setUpdateAt(user.getUpdateAt());
        userResponse.setRole(user.getRole().getRoleName());
        userResponse.setBankName(user.getBankName());

        return new BaseResponse<UserResponse>(WebConstants.BASE_SUCCESS, userResponse);
    }

    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse updateProfile(ProfileRequest profileRequest) {
        String name = profileRequest.getName();
        String email = profileRequest.getEmail();
        String bankName = profileRequest.getBankName();
        String bankAdress = profileRequest.getBankAdress();

        User user = currentUserDetails.getUserDetails();
        user.setBankAdress(bankAdress);
        user.setBankName(bankName);
        user.setEmail(email);
        user.setName(name);
        user.setUpdateAt(LocalDateTime.now());

        userRepository.save(user);

        return new BaseResponse(WebConstants.BASE_SUCCESS, null);

    }

    @Override
    public BaseResponse<ManagerUserResponse> managerUsers() {
        List<User> users = userRepository.findAll();
        int count = users.size();
        if (users.isEmpty())
            return new BaseResponse<ManagerUserResponse>(WebConstants.DONT_HAVE_USER_IN_SYSTEM, null);
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setAccount(user.getAccount());
            userResponse.setBalance(user.getWallet().getBalance());
            userResponse.setName(user.getName());
            userResponse.setEmail(user.getEmail());
            userResponse.setBankAdress(user.getBankAdress());
            userResponse.setCreateAt(user.getCreateAt());
            userResponse.setUpdateAt(user.getUpdateAt());
            userResponse.setRole(user.getRole().getRoleName());
            userResponse.setBankName(user.getBankName());

            userResponses.add(userResponse);
        }
        ManagerUserResponse managerUserResponse = new ManagerUserResponse();
        managerUserResponse.setCount(count);
        managerUserResponse.setUsers(userResponses);
        return new BaseResponse<ManagerUserResponse>(WebConstants.BASE_SUCCESS, managerUserResponse);
    }

    @Override
    public BaseResponse<String> deleteById(Long id) {
        Long currentUserId = currentUserDetails.getUserDetails().getId();
        if (id == currentUserId)
            return new BaseResponse<String>(WebConstants.ERROR_DELETE_THIS_ADMIN, null);
        userRepository.deleteById(id);
        return new BaseResponse<String>(WebConstants.BASE_SUCCESS, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse<String> changePassword(ChangePasswordRequest changePasswordRequest) {
        User currentUser = currentUserDetails.getUserDetails();
        String currentPassword = changePasswordRequest.getCurrentPassword();
        String newPassword = changePasswordRequest.getNewPassword();
        String confirmPassword = changePasswordRequest.getConfirmPassword();

        if (!newPassword.equals(confirmPassword))
            return new BaseResponse(WebConstants.CONFIRM_PASSWORD_NOT_MACTH, null);
        if (!passwordEncoder.matches(currentPassword, currentUser.getPassword()))
            return new BaseResponse<String>(WebConstants.PASSWORD_NOT_CORRECT, null);

        currentUser.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(currentUser);

        return new BaseResponse<String>(WebConstants.BASE_SUCCESS, null);
    }
}