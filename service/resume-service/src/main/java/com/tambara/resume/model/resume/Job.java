package com.tambara.resume.model.resume;

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
public class Job extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = -1426928026536273748L;

    //Purpose: Unique ID
    //Data Type: long
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long jid;

    //Purpose: Name of company
    //Data Type: String
    @NotNull
    private String name;

    //Purpose: Location of company
    //Data Type: String
    @NotNull
    private String location;

    //Purpose: Company website
    //Data Type: String
    private String websiteLink;

    //Purpose: Start of job
    //Data Type: java.util.Date
    @NotNull
    private Date started;

    //Purpose: End of job
    //Data Type: java.util.Date
    //Notes: If left NULL, then job will be considered as current.
    private Date ended;

    //Purpose: Job title
    //Data Type: String
    @NotNull
    private String title;

    //Purpose: Job summary
    //Data Type: String
    @NotNull
    private String jobSummary;

    //Purpose: Job foot notes. This will include accomplishments and projects.
    //Data Type: Set<String>

    public long getJid() {
        return jid;
    }

    public void setJid(long jid) {
        this.jid = jid;
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

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getEnded() {
        return ended;
    }

    public void setEnded(Date ended) {
        this.ended = ended;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJobSummary() {
        return jobSummary;
    }

    public void setJobSummary(String jobSummary) {
        this.jobSummary = jobSummary;
    }
}
