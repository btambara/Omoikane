package com.tambara.resume.service.contact;

import com.tambara.resume.model.contact.Contact;
import com.tambara.resume.service.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public interface ContactBaseService {
    Contact get() throws ResourceNotFoundException;

    Contact create(Contact c);

    Contact update(Contact c);
}
