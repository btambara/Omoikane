package com.tambara.resume.mapper.resume;

import com.tambara.resume.persistence.model.resume.Job;
import com.tambara.resume.web.dto.resume.JobDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class JobMapper {
    @Autowired
    private ModelMapper modelMapper;

    public JobDto convertToDto(Job j) {
        return modelMapper.map(j, JobDto.class);
    }

    public Job convertToModel(JobDto dto) {
        return modelMapper.map(dto, Job.class);
    }
}
