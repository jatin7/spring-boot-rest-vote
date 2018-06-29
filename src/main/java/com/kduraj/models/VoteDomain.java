package com.kduraj.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Written by Kevin Duraj
 */
@Entity
public class VoteDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String candidate;
    private Integer count;
    private Date creation;


    /**
     * Vote domain
     * @param candidate
     * @param count
     */
    public VoteDomain(String candidate, Integer count, Date creation) {

        this.setCandidate(candidate);
        this.setCount(count);
        this.setCreation(creation);
    }

    /**
     * Java Persistence API
     */
    public VoteDomain() { }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getCandidate() {

        return candidate;
    }

    public void setCandidate(String candidate) {

        this.candidate = candidate;
    }

    public Integer getCount() {

        return count;
    }

    public void setCount(Integer count) {

        this.count = count;
    }

    public String getCreation() {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        format.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        String formatted = format.format(creation);
        return formatted;

    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

}
