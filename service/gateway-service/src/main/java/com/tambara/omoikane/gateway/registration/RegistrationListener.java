package com.tambara.omoikane.gateway.registration;

import com.tambara.omoikane.gateway.persistence.model.User;
import com.tambara.omoikane.gateway.service.EmailBaseService;
import com.tambara.omoikane.gateway.service.VerificationTokenBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    @Autowired
    private EmailBaseService emailService;

    @Autowired
    private VerificationTokenBaseService verificationTokenService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        User user = onRegistrationCompleteEvent.getUser();

        String token = verificationTokenService.createVerificationToken(user);

        @SuppressWarnings("StringBufferReplaceableByString") StringBuilder message = new StringBuilder();
        message.append("<h3>Welcome!</h3>");
        message.append("Please click the link below to activate your account.<br>");
        message.append("<a href=\"");
        message.append(onRegistrationCompleteEvent.getAppUrl());
        message.append("/registration/confirm?token=");
        message.append(token);
        message.append("\"");
        message.append(">Validate email</a>");

        emailService.sendEmail(
                user.getEmail(),
                "Please confirm your email",
                message.toString()
        );
    }
}
