package com.interlink.cds.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.interlink.cds.jsonView.CompanyView;
import com.interlink.cds.jsonView.UserView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @NotNull
    @Size(min=2, max=50)
    @JsonView({CompanyView.Worker.class})
    private String name;

    @Column(length = 50)
    @JsonView({CompanyView.Worker.class})
    @Size(min=2, max=50)
    private String website;

    @OneToMany(mappedBy = "company")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<Job> jobs = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    @JsonView(UserView.Worker.class)
    private List<Phone> phones = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<Status> statuses = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<JobStatus> jobStatuses = new ArrayList<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "default_status")
    @JsonView(CompanyView.CompanyDetails.class)
    private Status defaultStatus;

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public List<JobStatus> getJobStatuses() {
        return jobStatuses;
    }

    public void setJobStatuses(List<JobStatus> jobStatuses) {
        this.jobStatuses = jobStatuses;
    }

    public Status getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(Status defaultStatus) {
        this.defaultStatus = defaultStatus;
    }
}
