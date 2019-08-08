package com.tambara.omoikane.gateway.service;

import com.tambara.omoikane.gateway.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public interface UserAuthenticationBaseService extends UserDetailsService {
    User registerUser(User user);

    String login(String username, String password);

    boolean logout(String token);

    boolean isValidToken(String token);

    String createNewToken(String token);
}
