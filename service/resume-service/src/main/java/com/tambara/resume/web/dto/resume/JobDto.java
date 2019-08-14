package com.tambara.resume.web.dto.resume;

import lombok.Data;

import java.util.Date;
import java.util.Set;

//Purpose: Data transfer object (DTO) for Job
@Data
public class JobDto {
    private Long jid;

    private String name;

    private String location;

    private String websiteLink;

    private Date started;

    private Date ended;

    private String title;

    private String jobSummary;

    private Set<String> jobFootnotes;
}
