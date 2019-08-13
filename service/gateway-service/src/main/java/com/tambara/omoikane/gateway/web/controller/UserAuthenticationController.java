package com.tambara.omoikane.gateway.web.controller;

import com.tambara.omoikane.gateway.mapper.UserMapper;
import com.tambara.omoikane.gateway.persistence.model.User;
import com.tambara.omoikane.gateway.service.EmailBaseService;
import com.tambara.omoikane.gateway.service.UserAuthenticationBaseService;
import com.tambara.omoikane.gateway.web.dto.LoginRequest;
import com.tambara.omoikane.gateway.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserAuthenticationController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAuthenticationBaseService userAuthenticationService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailBaseService emailService;

    @Bean
    public UserMapper createUserMapper() {
        return new UserMapper();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        emailService.sendEmail("btambara@gmail.com", "Crap", "Crap");

        userAuthenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return new ResponseEntity<>("Logged in user " + loginRequest.getUsername(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        userDto.setId(-1L);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userAuthenticationService.registerUser(userMapper.convertToContact(userDto));
        return new ResponseEntity<>("Created user " + user.getUsername(), HttpStatus.CREATED);
    }
}
