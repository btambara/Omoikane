package com.tambara.resume.service.contact;

import com.tambara.resume.persistence.dao.contact.ContactRepo;
import com.tambara.resume.persistence.model.contact.Contact;
import com.tambara.resume.service.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Purpose: The service class for personal contact information.
//Notes:
//1) Only ONE entry should be in this table.
@Service
public class ContactService implements ContactBaseService {
    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact get() {
        return contactRepo.findAll().stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Unable to locate contact."));
    }

    //Purpose: Creates a Contact and if a Contact is created, then it will update it.
    @Override
    public Contact create(Contact c) {
        if (!contactRepo.findAll().isEmpty()) {
            return update(c);
        }

        return contactRepo.save(c);
    }

    //Purpose: Updates the Contact and if a Contact isn't created, then it will create one.
    @Override
    public Contact update(Contact c) {
        return contactRepo
                .findAll()
                .stream()
                .findFirst()
                .map(contact -> {
                    contact.setFirstName(c.getFirstName());
                    contact.setLastName(c.getLastName());
                    contact.setMessage(c.getMessage());
                    contact.setEmail(c.getEmail());
                    contact.setGithubAddress(c.getGithubAddress());
                    contact.setLinkedinAddress(c.getLinkedinAddress());
                    return contactRepo.save(contact);
                })
                .orElseGet(() -> create(c));
    }
}
