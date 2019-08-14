package com.tambara.omoikane.gateway.persistence.dao;

import com.tambara.omoikane.gateway.persistence.model.PasswordResetToken;
import com.tambara.omoikane.gateway.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);
}
