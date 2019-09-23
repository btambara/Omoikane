package com.tambara.resume.web.dto.resume;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

//Purpose: Data transfer object (DTO) for Education
@Data
public class EducationDto {
    private Long eid;

    private String name;

    private String location;

    private String websiteLink;

    private String certification;

    private LocalDate started;

    private LocalDate ended;
}
