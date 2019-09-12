package com.tambara.resume.web.dto.coverletter;

import lombok.ToString;

//Purpose: Data transfer object (DTO) for Cover Letter
@ToString
public class CoverLetterDto {
    private Long clid;
    private String headline;
    private String coverLetter;

    public Long getClid() {
        return clid;
    }

    public void setClid(Long clid) {
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