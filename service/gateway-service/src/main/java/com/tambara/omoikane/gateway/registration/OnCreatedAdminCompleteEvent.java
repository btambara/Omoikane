package com.tambara.omoikane.gateway.registration;

import com.tambara.omoikane.gateway.persistence.model.User;
import org.springframework.context.ApplicationEvent;

public class OnCreatedAdminCompleteEvent extends ApplicationEvent {
    private User user;
    private String password;

    public OnCreatedAdminCompleteEvent(User user, String password) {
        super(user);
        this.user = user;
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}