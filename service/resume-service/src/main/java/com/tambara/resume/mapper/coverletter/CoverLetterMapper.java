package com.tambara.resume.mapper.coverletter;

import com.tambara.resume.persistence.model.coverletter.CoverLetter;
import com.tambara.resume.web.dto.coverletter.CoverLetterDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

//Purpose: Converts Cover Letter data transfer object (DTO) to Cover Letter and vice versa.
public class CoverLetterMapper {
    @Autowired
    private ModelMapper modelMapper;

    public CoverLetterDto convertToCoverLetterDto(CoverLetter cl) {
        return modelMapper.map(cl, CoverLetterDto.class);
    }

    public CoverLetter convertToCoverLetter(CoverLetterDto cldto) {
        return modelMapper.map(cldto, CoverLetter.class);
    }
}
