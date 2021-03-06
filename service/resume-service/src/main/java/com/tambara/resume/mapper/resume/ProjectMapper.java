package com.tambara.resume.mapper.resume;

import com.tambara.resume.persistence.model.resume.Project;
import com.tambara.resume.web.dto.resume.ProjectDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ProjectDto convertToDto(Project j) {
        return modelMapper.map(j, ProjectDto.class);
    }

    public Project convertToModel(ProjectDto dto) {
        return modelMapper.map(dto, Project.class);
    }
}
