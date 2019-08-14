package com.tambara.resume.persistence.model.resume;

import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@ToString
public class Project extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 607919267638781995L;

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
    private String summary;

    //Purpose: Start of project
    //Data Type: java.util.Date
    @NotNull
    private Date projectStart;

    //Purpose: End of job
    //Data Type: java.util.Date
    //Notes: If left NULL, then the project will be considered as current.
    private Date projectEnd;

    //Purpose: Project website
    //Data Type: String
    @NotNull
    private String websiteLink;

    //Purpose: Project repository
    //Data Type: String
    @NotNull
    private String repositoryLink;

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

    public Date getProjectStart() {
        return projectStart;
    }

    public void setProjectStart(Date projectStart) {
        this.projectStart = projectStart;
    }

    public Date getProjectEnd() {
        return projectEnd;
    }

    public void setProjectEnd(Date projectEnd) {
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
}
