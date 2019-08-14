package com.tambara.omoikane.gateway.web.controller;

import com.tambara.omoikane.gateway.persistence.model.PasswordResetToken;
import com.tambara.omoikane.gateway.persistence.model.User;
import com.tambara.omoikane.gateway.security.OnPasswordResetEvent;
import com.tambara.omoikane.gateway.service.PasswordResetTokenBaseService;
import com.tambara.omoikane.gateway.service.UserAuthenticationBaseService;
import com.tambara.omoikane.gateway.web.dto.LoginRequest;
import com.tambara.omoikane.gateway.web.dto.PasswordChangeRequest;
import com.tambara.omoikane.gateway.web.dto.PasswordResetRequest;
import com.tambara.omoikane.gateway.web.exception.HttpErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Controller
@RequestMapping("/user")
public class UserAuthenticationController {
    @Autowired
    private UserAuthenticationBaseService userAuthenticationService;

    @Autowired
    private PasswordResetTokenBaseService passwordResetTokenService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        userAuthenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return new ResponseEntity<>("Logged in user " + loginRequest.getUsername(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/password/reset/request")
    public ResponseEntity<String> requestResetUserPassword(@RequestParam("username") final String username,
                                                           final HttpServletRequest request) {
        final String appUrl = "http://" + request.getServerName() + ":" +
                request.getServerPort() + request.getContextPath();

        User user = userAuthenticationService.getUser(username);

        eventPublisher.publishEvent(new OnPasswordResetEvent(user, appUrl));

        return new ResponseEntity<>("Started the process of a password reset.", HttpStatus.ACCEPTED);
    }

    @PostMapping("/password/reset")
    public ResponseEntity<String> resetUserPassword(@RequestParam("token") final String token,
                                                    @RequestBody PasswordResetRequest request) {


        //Never NULL
        PasswordResetToken passwordResetToken = passwordResetTokenService.getPasswordResetToken(token);

        final Calendar cal = Calendar.getInstance();
        if ((passwordResetToken.getTokenExpiration().getTime() - cal.getTime().getTime()) <= 0) {
            throw new HttpErrorException("Token has expired. Please submit reset again.", HttpStatus.CONFLICT);
        }

        userAuthenticationService.resetUserPassword(request.getUsername(), token, request.getNewPassword());

        return new ResponseEntity<>("Password reset.", HttpStatus.ACCEPTED);
    }

    @PutMapping("/password")
    public ResponseEntity<String> updateUserPassword(@RequestBody PasswordChangeRequest request) {
        userAuthenticationService.updateUserPassword(request.getUsername(), request.getOldPassword(),
                request.getNewPassword()
        );

        return new ResponseEntity<>("Password has been changed.", HttpStatus.ACCEPTED);
    }
}
