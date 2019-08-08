package com.tambara.omoikane.gateway.mapper;

import com.tambara.omoikane.gateway.mapper.dto.UserDto;
import com.tambara.omoikane.gateway.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

//Purpose: Converts User data transfer object (DTO) to User and vice versa.
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;

    public UserDto convertToContactDto(User u) {
        return modelMapper.map(u, UserDto.class);
    }

    public User convertToContact(UserDto dto) {
        return modelMapper.map(dto, User.class);
    }
}
