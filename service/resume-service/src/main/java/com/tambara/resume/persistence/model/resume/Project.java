package com.tambara.resume.persistence.model.resume;

import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@ToString
public class Project extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 2087573530763336852L;

    //Purpose: Unique ID
    //Data Type: long
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pid;

    //Purpose: Name of company
    //Data Type: String
    @NotNull
    private String name;

    //Purpose: Location of company
    //Data Type: String
    @NotNull
    private String location;

    //Purpose: Project title
    //Data Type: String
    @NotNull
    private String title;

    //Purpose: Project summary
    //Data Type: String
    @NotNull
    @Column(length=2500)
    private String summary;

    //Purpose: Start of project
    //Data Type: java.time
    @NotNull
    private LocalDate projectStart;

    //Purpose: End of job
    //Data Type: java.time
    //Notes: If left NULL, then the project will be considered as current.
    private LocalDate projectEnd;

    //Purpose: Project website
    //Data Type: String
    @NotNull
    private String websiteLink;

    //Purpose: Project repository
    //Data Type: String
    @NotNull
    private String repositoryLink;

    //Purpose: The technical environment of the job.
    //Data Type: Set<String>
    private ArrayList<String> technicalEnvironment;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public LocalDate getProjectStart() {
        return projectStart;
    }

    public void setProjectStart(LocalDate projectStart) {
        this.projectStart = projectStart;
    }

    public LocalDate getProjectEnd() {
        return projectEnd;
    }

    public void setProjectEnd(LocalDate projectEnd) {
        this.projectEnd = projectEnd;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public String getRepositoryLink() {
        return repositoryLink;
    }

    public void setRepositoryLink(String repositoryLink) {
        this.repositoryLink = repositoryLink;
    }

    public ArrayList<String> getTechnicalEnvironment() {
        return technicalEnvironment;
    }

    public void setTechnicalEnvironment(ArrayList<String> technicalEnvironment) {
        this.technicalEnvironment = technicalEnvironment;
    }
}
