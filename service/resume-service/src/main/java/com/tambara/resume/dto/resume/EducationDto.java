package com.tambara.resume.dto.resume;

import lombok.Data;

import java.util.Date;

//Purpose: Data transfer object (DTO) for Education
@Data
public class EducationDto {
    private Long eid;

    private String name;

    private String location;

    private String websiteLink;

    private String certification;

    private Date started;

    private Date ended;
}
