package com.tambara.omoikane.gateway.service;

import com.tambara.omoikane.gateway.persistence.model.PasswordResetToken;
import com.tambara.omoikane.gateway.persistence.model.User;

public interface PasswordResetTokenBaseService {
    String createPasswordResetToken(User user);

    boolean removePasswordResetToken(PasswordResetToken token);

    PasswordResetToken getPasswordResetToken(User user);

    PasswordResetToken getPasswordResetToken(String token);
}
