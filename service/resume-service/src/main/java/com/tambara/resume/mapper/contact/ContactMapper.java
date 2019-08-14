package com.tambara.resume.mapper.contact;

import com.tambara.resume.dto.contact.ContactDto;
import com.tambara.resume.model.contact.Contact;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

//Purpose: Converts Contact data transfer object (DTO) to Contact and vice versa.
public class ContactMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ContactDto convertToContactDto(Contact c) {
        return modelMapper.map(c, ContactDto.class);
    }

    public Contact convertToContact(ContactDto cdto) {
        return modelMapper.map(cdto, Contact.class);
    }
}
