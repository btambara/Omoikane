package com.tambara.resume.mapper.resume;

import com.tambara.resume.persistence.model.resume.Statement;
import com.tambara.resume.web.dto.resume.StatementDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class StatementMapper {
    @Autowired
    private ModelMapper modelMapper;

    public StatementDto convertToDto(Statement j) {
        return modelMapper.map(j, StatementDto.class);
    }

    public Statement convertToModel(StatementDto dto) {
        return modelMapper.map(dto, Statement.class);
    }
}
