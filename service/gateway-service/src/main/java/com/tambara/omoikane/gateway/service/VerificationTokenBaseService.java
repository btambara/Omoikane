package com.tambara.omoikane.gateway.service;

import com.tambara.omoikane.gateway.persistence.model.User;
import com.tambara.omoikane.gateway.persistence.model.VerificationToken;

public interface VerificationTokenBaseService {
    String createVerificationToken(User user);

    boolean removeVerificationToken(VerificationToken token);

    VerificationToken getVerificationToken(User user);

    VerificationToken getVerificationToken(String token);
}
