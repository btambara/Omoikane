package com.tambara.resume.mapper.resume;

import com.tambara.resume.dto.resume.ProjectDto;
import com.tambara.resume.model.resume.Project;
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
