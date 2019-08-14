package com.tambara.resume.mapper.resume;

import com.tambara.resume.persistence.model.resume.Education;
import com.tambara.resume.web.dto.resume.EducationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class EducationMapper {
    @Autowired
    private ModelMapper modelMapper;

    public EducationDto convertToDto(Education e) {
        return modelMapper.map(e, EducationDto.class);
    }

    public Education convertToModel(EducationDto dto) {
        return modelMapper.map(dto, Education.class);
    }
}
