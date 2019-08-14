package com.tambara.omoikane.gateway.service;

import com.tambara.omoikane.gateway.persistence.dao.UserRepository;
import com.tambara.omoikane.gateway.persistence.model.Privilege;
import com.tambara.omoikane.gateway.persistence.model.Role;
import com.tambara.omoikane.gateway.persistence.model.User;
import com.tambara.omoikane.gateway.web.exception.HttpErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserAuthenticationService implements UserAuthenticationBaseService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                getAuthorities(user.getRoles())
        );

    }

    private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
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
    public void updateEnabledAccount(String username, boolean isEnabled) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setEnabled(isEnabled);
            userRepository.save(user);
        } else {
            throw new HttpErrorException("Username does not exist", HttpStatus.CONFLICT);
        }
    }

    @Override
    public void updateUserPassword(String username, String currentPassword, String newPassword) {
        User user = userRepository.findByUsername(username);

        if (currentPassword == null || newPassword == null) {
            throw new HttpErrorException("Password cannot be NULL.", HttpStatus.CONFLICT);
        }

        if (currentPassword.equals(newPassword)) {
            throw new HttpErrorException("Passwords are the same", HttpStatus.CONFLICT);
        }

        if (user != null) {
            if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
                throw new HttpErrorException("Incorrect current password", HttpStatus.CONFLICT);
            }

            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new HttpErrorException("Username does not exist", HttpStatus.CONFLICT);
        }
    }

    @Override
    public void resetUserPassword(String username, String resetToken, String newPassword) {
        User user = userRepository.findByUsername(username);

        if (newPassword == null) {
            throw new HttpErrorException("Password cannot be NULL.", HttpStatus.CONFLICT);
        }

        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new HttpErrorException("Username does not exist.", HttpStatus.CONFLICT);
        }
    }

    @Override
    public User getUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new HttpErrorException("Username does not exist.", HttpStatus.CONFLICT);
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
