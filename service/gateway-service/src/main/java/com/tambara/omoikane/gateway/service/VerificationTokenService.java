package com.tambara.omoikane.gateway.service;

import com.tambara.omoikane.gateway.persistence.dao.VerificationTokenRepository;
import com.tambara.omoikane.gateway.persistence.model.User;
import com.tambara.omoikane.gateway.persistence.model.VerificationToken;
import com.tambara.omoikane.gateway.web.exception.HttpErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class VerificationTokenService implements VerificationTokenBaseService {

    @Value("${verification.token.expiration.limit}")
    private int expirationLimit;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public String createVerificationToken(User user) {
        String generatedToken = UUID.randomUUID().toString();

        VerificationToken tempToken =
                new VerificationToken(generatedToken, user, calculateExpiryDate(expirationLimit));

        return verificationTokenRepository.save(tempToken).getToken();
    }

    @Override
    public boolean removeVerificationToken(VerificationToken token) {
        if (token != null) {
            verificationTokenRepository.delete(token);
            return true;
        } else {
            throw new HttpErrorException("Unable to remove. This Verification token can't be found for this user.", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public VerificationToken getVerificationToken(User user) {
        VerificationToken temp = verificationTokenRepository.findByUser(user);
        if (temp != null) {
            return temp;
        }

        throw new HttpErrorException("No Verification token can be found for this user.", HttpStatus.BAD_REQUEST);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        VerificationToken temp = verificationTokenRepository.findByToken(token);
        if (temp != null) {
            return temp;
        }

        throw new HttpErrorException("No Verification token can be found for this token.", HttpStatus.BAD_REQUEST);
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
