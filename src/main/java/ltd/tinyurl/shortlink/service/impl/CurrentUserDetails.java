package ltd.tinyurl.shortlink.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.Data;
import ltd.tinyurl.shortlink.entity.User;
import ltd.tinyurl.shortlink.repository.UserRepository;

@Service
@Data
public class CurrentUserDetails {

    private final UserRepository userRepository;

    // lay user trong request
    public User getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        // Lấy UserDetails từ Authentication
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByAccount(userDetails.getUsername());
        return user;
    }
}
