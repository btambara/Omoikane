package com.tambara.omoikane.gateway.service;

import com.tambara.omoikane.gateway.persistence.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public interface UserAuthenticationBaseService extends UserDetailsService {
    User registerUser(User user);

    String login(String username, String password);

    void updateEnabledAccount(String username, boolean isEnabled);

    void updateUserPassword(String username, String currentPassword, String newPassword);

    void resetUserPassword(String username, String resetToken, String newPassword);

    User getUser(String username);

    boolean logout(String token);

    boolean isValidToken(String token);

    String createNewToken(String token);
}
