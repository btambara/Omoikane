package com.tambara.resume.web.dto.contact;

import lombok.ToString;

//Purpose: Data transfer object (DTO) for Contact
@ToString
public class ContactDto {

    private Long cid;

    private String firstName;

    private String lastName;

    private String email;

    private String githubAddress;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithubAddress() {
        return githubAddress;
    }

    public void setGithubAddress(String githubAddress) {
        this.githubAddress = githubAddress;
    }
}
