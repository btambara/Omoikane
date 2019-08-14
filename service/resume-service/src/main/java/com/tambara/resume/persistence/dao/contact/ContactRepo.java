package com.tambara.resume.persistence.dao.contact;

import com.tambara.resume.persistence.model.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ContactRepo extends JpaRepository<Contact, Long> {
}
