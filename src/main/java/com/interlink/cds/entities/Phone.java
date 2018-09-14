package com.interlink.cds.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.interlink.cds.jsonView.PhoneView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    @NotNull
    @JsonView({PhoneView.Worker.class})
    private String number;

    @Column(length = 25)
    @JsonView({PhoneView.Worker.class})
    private String type;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(mappedBy = "phones")
    private List<Job> jobs = new ArrayList<>();

    @ManyToMany(mappedBy = "phones")
    private List<Job> users = new ArrayList<>();

    public Phone() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Job> getUsers() {
        return users;
    }

    public void setUsers(List<Job> users) {
        this.users = users;
    }
}
