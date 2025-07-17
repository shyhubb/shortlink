package ltd.tinyurl.shortlink.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.Data;
import ltd.tinyurl.shortlink.dto.request.LoginRequest;
import ltd.tinyurl.shortlink.dto.request.UserRequest;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.LoginResponse;
import ltd.tinyurl.shortlink.entity.Role;
import ltd.tinyurl.shortlink.entity.User;
import ltd.tinyurl.shortlink.entity.Wallet;
import ltd.tinyurl.shortlink.repository.RoleRepository;
import ltd.tinyurl.shortlink.repository.UserRepository;
import ltd.tinyurl.shortlink.security.JwtTokenProvider;
import ltd.tinyurl.shortlink.service.AuthService;
import ltd.tinyurl.shortlink.webconstants.WebConstants;

@Service
@Data
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final RoleRepository roleRepository;

    private final WalletServiceImpl walletServiceImpl;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public BaseResponse register(UserRequest userRequest) {
        String account = userRequest.getAccount();
        String password = userRequest.getPassword();
        String confirmPassword = userRequest.getConfirmPassword();
        if (!password.equals(confirmPassword))
            return new BaseResponse<>(WebConstants.CONFIRM_PASSWORD_NOT_MACTH, null);
        if (userRepository.existsByAccount(account))
            return new BaseResponse<>(WebConstants.ACCOUNT_EXITS, null);
        User user = new User();
        Optional<Role> roleOptional = roleRepository.findByRoleName(WebConstants.ROLE_USER);
        if (!roleOptional.isPresent())
            return new BaseResponse<>(WebConstants.SYSTEM_ERROR_FIND_ROLE, null);
        Role role = roleOptional.get();
        user.setAccount(account);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        user.setCreateAt(LocalDateTime.now());
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        user.setWallet(wallet);

        userRepository.save(user);
        walletServiceImpl.createWallet(wallet);

        return new BaseResponse<>(WebConstants.BASE_SUCCESS, null);
    }

    @Override
    public BaseResponse<LoginResponse> login(LoginRequest loginRequest) {
        String account = loginRequest.getAccount();
        String password = loginRequest.getPassword();
        User user = userRepository.findByAccount(account);
        String role = user.getRole().getRoleName();
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtTokenProvider.generateToken(loginRequest.getAccount(), role);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            return new BaseResponse<LoginResponse>(WebConstants.BASE_SUCCESS, loginResponse);
        }
        return new BaseResponse<LoginResponse>(WebConstants.BASE_FAIL, null);
    }
}
