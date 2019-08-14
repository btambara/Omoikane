package com.tambara.omoikane.gateway.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PasswordResetToken {
    //Purpose: Unique ID
    //Data Type: long
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date tokenExpiration;

    public PasswordResetToken() {
    }

    public PasswordResetToken(String token, User user, Date tokenExpiration) {
        this.token = token;
        this.user = user;
        this.tokenExpiration = tokenExpiration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(Date tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }
}
