package com.tambara.resume.web.dto.resume;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

//Purpose: Data transfer object (DTO) for Project
@Data
public class ProjectDto {

    private Long pid;

    private String name;

    private String location;

    private String title;

    private String summary;

    private LocalDate projectStart;

    private LocalDate projectEnd;

    private String websiteLink;

    private String repositoryLink;
}
