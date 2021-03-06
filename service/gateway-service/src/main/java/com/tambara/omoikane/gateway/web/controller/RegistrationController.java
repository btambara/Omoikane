package com.tambara.omoikane.gateway.web.controller;

import com.tambara.omoikane.gateway.mapper.UserMapper;
import com.tambara.omoikane.gateway.persistence.dao.RoleRepository;
import com.tambara.omoikane.gateway.persistence.model.Role;
import com.tambara.omoikane.gateway.persistence.model.User;
import com.tambara.omoikane.gateway.persistence.model.VerificationToken;
import com.tambara.omoikane.gateway.registration.OnRegistrationCompleteEvent;
import com.tambara.omoikane.gateway.service.UserAuthenticationBaseService;
import com.tambara.omoikane.gateway.service.VerificationTokenBaseService;
import com.tambara.omoikane.gateway.web.dto.UserDto;
import com.tambara.omoikane.gateway.web.exception.HttpErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

@Controller
@CrossOrigin
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserAuthenticationBaseService userAuthenticationService;

    @Autowired
    private VerificationTokenBaseService verificationTokenService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserDto userDto, final HttpServletRequest request) {
        userDto.setId(-1L);
        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole != null) {
            userDto.setRoles(new ArrayList<>(Collections.singletonList(userRole)));
        }
        userDto.setEnabled(false);
        userDto.setAccountNonExpired(true);
        userDto.setCredentialsNonExpired(true);
        userDto.setAccountNonLocked(true);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User user = userAuthenticationService.registerUser(userMapper.convertToContact(userDto));

        final String appUrl = "http://" + request.getServerName() + ":" +
                request.getServerPort() + request.getContextPath();

        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, appUrl));

        return new ResponseEntity<>("Created user " + user.getUsername(), HttpStatus.CREATED);
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmRegistration(@RequestParam("token") final String token) {
        //Never NULL
        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);

        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getTokenExpiration().getTime() - cal.getTime().getTime()) <= 0) {
            throw new HttpErrorException("Token has expired", HttpStatus.CONFLICT);
        }

        boolean result = verificationTokenService.removeVerificationToken(verificationToken);
        if (result) {
            userAuthenticationService.updateEnabledAccount(
                    verificationToken.getUser().getUsername(), true);
        }

        return new ResponseEntity<>("Removed token", HttpStatus.CREATED);
    }
}
