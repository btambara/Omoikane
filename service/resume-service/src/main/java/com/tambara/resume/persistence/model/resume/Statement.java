package com.tambara.resume.persistence.model.resume;

import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@ToString
public class Statement implements Serializable {

    private static final long serialVersionUID = 3632989300836102175L;

    //Purpose: Unique ID
    //Data Type: long
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sid;

    //Purpose: The statement for the resume
    //Data Type: String
    @NotNull
    @Column(length=2500)
    private String statement;

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}
