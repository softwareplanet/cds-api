package com.interlink.cds.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.interlink.cds.jsonView.JobView;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;


@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JobView.ShotDeatils.class)
    private Long id;

    @Column(length = 50)
    @JsonView(JobView.ShotDeatils.class)
    @NotNull
    private String title;

    @Column
    @JsonView(JobView.Worker.class)
    private String description;

    @Column
    @NotNull
    @JsonView(JobView.Worker.class)
    private String location;

    @Column(length = 50)
    @NotNull
    @JsonView(JobView.Worker.class)
    private String customerName;

    @JsonView(JobView.Worker.class)
    @Column
    @Email
    private String eMail;

    @OneToOne
    @JoinColumn(name = "assignee_id")
    @JsonView(JobView.Worker.class)
    private User assignee;

    @OneToOne
    @JoinColumn(name ="status_id")
    @JsonView(JobView.Worker.class)
    private Status status;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "jobs_phones",
            joinColumns = {@JoinColumn(name = "job_id")},
            inverseJoinColumns = {@JoinColumn(name = "phone_id")}
    )
    @JsonView(JobView.Worker.class)
    @NotNull
    private List<Phone> phones = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonView(JobView.Worker.class)
    private Company company;


    @OneToMany(mappedBy = "job")
    private List<JobStatus> jobStatuses = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Future
    @JsonView(JobView.Worker.class)
    private Date dueDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(JobView.Worker.class)
    private Date createdDate;

    public Job() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<JobStatus> getJobStatuses() {
        return jobStatuses;
    }

    public void setJobStatuses(List<JobStatus> jobStatuses) {
        this.jobStatuses = jobStatuses;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
