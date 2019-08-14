package com.tambara.omoikane.gateway.security;

import com.tambara.omoikane.gateway.persistence.model.User;
import org.springframework.context.ApplicationEvent;

public class OnPasswordResetEvent extends ApplicationEvent {
    private User user;
    private String appUrl;

    public OnPasswordResetEvent(User user, String appUrl) {
        super(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }
}
