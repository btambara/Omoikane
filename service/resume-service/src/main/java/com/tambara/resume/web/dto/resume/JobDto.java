package com.tambara.resume.web.dto.resume;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

//Purpose: Data transfer object (DTO) for Job
@Data
public class JobDto {
    private Long jid;

    private String name;

    private String location;

    private String websiteLink;

    private LocalDate started;

    private LocalDate ended;

    private String title;

    private String jobSummary;

    private ArrayList<String> jobFootnotes;

    private ArrayList<String> technicalEnvironment;
}
