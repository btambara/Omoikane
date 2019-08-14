package com.tambara.omoikane.gateway.service;

import com.tambara.omoikane.gateway.persistence.dao.PasswordResetTokenRepository;
import com.tambara.omoikane.gateway.persistence.model.PasswordResetToken;
import com.tambara.omoikane.gateway.persistence.model.User;
import com.tambara.omoikane.gateway.web.exception.HttpErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class PasswordResetTokenService implements PasswordResetTokenBaseService {

    @Value("${password.reset.token.expiration.limit}")
    private int expirationLimit;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public String createPasswordResetToken(User user) {
        String generatedToken = UUID.randomUUID().toString();

        PasswordResetToken tempToken =
                new PasswordResetToken(generatedToken, user, calculateExpiryDate(expirationLimit));

        return passwordResetTokenRepository.save(tempToken).getToken();
    }

    @Override
    public boolean removePasswordResetToken(PasswordResetToken token) {
        if (token != null) {
            passwordResetTokenRepository.delete(token);
            return true;
        } else {
            throw new HttpErrorException("Unable to remove. This Password reset token can't be found for this user.", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public PasswordResetToken getPasswordResetToken(User user) {
        PasswordResetToken temp = passwordResetTokenRepository.findByUser(user);
        if (temp != null) {
            return temp;
        }

        throw new HttpErrorException("No Password reset token can be found for this user.", HttpStatus.BAD_REQUEST);
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        PasswordResetToken temp = passwordResetTokenRepository.findByToken(token);
        if (temp != null) {
            return temp;
        }

        throw new HttpErrorException("No Password reset token can be found for this token.", HttpStatus.BAD_REQUEST);
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
