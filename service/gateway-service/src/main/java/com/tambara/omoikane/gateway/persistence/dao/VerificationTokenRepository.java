package com.tambara.omoikane.gateway.persistence.dao;

import com.tambara.omoikane.gateway.persistence.model.User;
import com.tambara.omoikane.gateway.persistence.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
