package com.tambara.omoikane.gateway.registration;

import com.tambara.omoikane.gateway.persistence.model.User;
import com.tambara.omoikane.gateway.service.EmailBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CreatedAdminListener implements ApplicationListener<OnCreatedAdminCompleteEvent> {
    @Autowired
    private EmailBaseService emailService;

    @Override
    public void onApplicationEvent(OnCreatedAdminCompleteEvent onCreatedAdminCompleteEvent) {
        User user = onCreatedAdminCompleteEvent.getUser();

        @SuppressWarnings("StringBufferReplaceableByString") StringBuilder message = new StringBuilder();
        message.append("<h3>Welcome!</h3>");
        message.append("Please see your auto generated password below.<br>");
        message.append("Password: <b>" + onCreatedAdminCompleteEvent.getPassword());
        message.append("</b><br>ALWAYS CHANGE YOUR PASSWORD AFTER LOGGING IN!");

        emailService.sendEmail(
                user.getEmail(),
                "Account password",
                message.toString()
        );
    }
}
