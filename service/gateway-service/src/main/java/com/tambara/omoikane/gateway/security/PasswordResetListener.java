package com.tambara.omoikane.gateway.security;

import com.tambara.omoikane.gateway.persistence.model.User;
import com.tambara.omoikane.gateway.service.EmailBaseService;
import com.tambara.omoikane.gateway.service.PasswordResetTokenBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PasswordResetListener implements ApplicationListener<OnPasswordResetEvent> {
    @Autowired
    private EmailBaseService emailService;

    @Autowired
    private PasswordResetTokenBaseService passwordResetTokenService;

    @Override
    public void onApplicationEvent(OnPasswordResetEvent onPasswordResetEvent) {
        User user = onPasswordResetEvent.getUser();

        String token = passwordResetTokenService.createPasswordResetToken(user);

        @SuppressWarnings("StringBufferReplaceableByString") StringBuilder message = new StringBuilder();
        message.append("<h3>A password reset has been request!</h3>");
        message.append("Please click the link below to reset your account.<br>");
//        message.append("<a href=\"");
//        message.append(onPasswordResetEvent.getAppUrl());
//        message.append("/registration/confirm?token=");
        message.append(token);
//        message.append("\"");
//        message.append(">Validate email</a>");

        emailService.sendEmail(
                user.getEmail(),
                "Your password has been requested to be reset",
                message.toString()
        );
    }
}
