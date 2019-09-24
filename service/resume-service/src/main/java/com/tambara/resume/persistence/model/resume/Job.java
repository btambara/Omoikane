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
public class Job extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 5661895021471339075L;

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
    //Data Type: java.time
    @NotNull
    private LocalDate started;

    //Purpose: End of job
    //Data Type: java.time
    //Notes: If left NULL, then job will be considered as current.
    private LocalDate ended;

    //Purpose: Job title
    //Data Type: String
    @NotNull
    private String title;

    //Purpose: Job summary
    //Data Type: String
    @NotNull
    @Column(length = 2500)
    private String jobSummary;

    //Purpose: Job foot notes. This will include accomplishments and projects.
    //Data Type: Set<String>
    @Column(length = 1500)
    private ArrayList<String> jobFootnotes;

    //Purpose: The technical environment of the job.
    //Data Type: Set<String>
    private ArrayList<String> technicalEnvironment;

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

    public LocalDate getStarted() {
        return started;
    }

    public void setStarted(LocalDate started) {
        this.started = started;
    }

    public LocalDate getEnded() {
        return ended;
    }

    public void setEnded(LocalDate ended) {
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

    public ArrayList<String> getJobFootnotes() {
        return jobFootnotes;
    }

    public void setJobFootnotes(ArrayList<String> jobFootnotes) {
        this.jobFootnotes = jobFootnotes;
    }

    public ArrayList<String> getTechnicalEnvironment() {
        return technicalEnvironment;
    }

    public void setTechnicalEnvironment(ArrayList<String> technicalEnvironment) {
        this.technicalEnvironment = technicalEnvironment;
    }
}
