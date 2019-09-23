package com.tambara.resume.persistence.model.resume;

import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@ToString
public class Education extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 5789509026865717077L;

    //Purpose: Unique ID
    //Data Type: long
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eid;

    //Purpose: Name of school
    //Data Type: String
    @NotNull
    private String name;

    //Purpose: Location of school
    //Data Type: String
    @NotNull
    private String location;

    //Purpose: School website
    //Data Type: String
    private String websiteLink;

    //Purpose: Name of certification
    //Data Type: String
    @NotNull
    private String certification;

    //Purpose: Start of certification
    //Data Type: java.util.Date
    @NotNull
    private LocalDate started;

    //Purpose: End of certification
    //Data Type: java.util.Date
    @NotNull
    private LocalDate ended;

    public long getEid() {
        return eid;
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

    public void setEid(long eid) {
        this.eid = eid;
    }


    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
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
}
