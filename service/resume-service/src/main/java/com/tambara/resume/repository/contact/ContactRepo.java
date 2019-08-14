package com.tambara.resume.repository.contact;

import com.tambara.resume.model.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ContactRepo extends JpaRepository<Contact, Long> {
}
