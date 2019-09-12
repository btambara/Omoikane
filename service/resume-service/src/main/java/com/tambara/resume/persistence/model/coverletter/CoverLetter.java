package com.tambara.resume.persistence.model.coverletter;

import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@ToString
public class CoverLetter implements Serializable {

    private static final long serialversionuid = -6879800058909867751l;

    //Purpose: Unique ID
    //Data Type: long
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long clid;

    //Purpose: The headline for the cover letter
    //Data Type: String
    @NotNull
    private String headline;

    //Purpose: The cover letter
    //Data Type: String
    @NotNull
    @Column(length=2500)
    private String coverLetter;

    public long getClid() {
        return clid;
    }

    public void setClid(long clid) {
        this.clid = clid;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
}
