package com.tambara.omoikane.gateway.service;

import com.tambara.omoikane.gateway.exception.HttpErrorException;
import com.tambara.omoikane.gateway.mapper.dto.UserDto;
import com.tambara.omoikane.gateway.model.User;
import com.tambara.omoikane.gateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService implements UserAuthenticationBaseService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());

        if (foundUser == null) {
            return userRepository.save(user);
        }
        throw new HttpErrorException("Username already exist", HttpStatus.CONFLICT);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);

        return new UserDto(user.getUsername(), user.getPassword(),
                user.isAccountNonExpired(), user.isAccountNonLocked(),
                user.isCredentialsNonExpired(), user.isEnabled());
    }

    @Override
    public String login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                    password));
            return "JWT TOKEN";
        } catch (Exception e) {
            throw new HttpErrorException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public boolean logout(String token) {
        return false;
    }

    @Override
    public boolean isValidToken(String token) {
        return false;
    }

    @Override
    public String createNewToken(String token) {
        return null;
    }
}
