package com.interlink.cds.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.interlink.cds.jsonView.UserView;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(UserView.Manager.class)
    private Long id;

    @Column(length = 50)
    @Size(min = 3, max = 25)
    @NotNull
    @JsonView(UserView.Worker.class)
    private String username;

    @Column
    @NotNull
    @Size(min=8)
    private String password;

    @Column(length = 50)
    @NotNull
    @Size(max = 25)
    @JsonView(UserView.Worker.class)
    private String firstName;

    @Column
    @NotNull
    @Size(max = 25)
    @JsonView(UserView.Manager.class)
    private String lastName;

    @Column(unique = true)
    @Email
    @NotNull
    @JsonView(UserView.Manager.class)
    private String eMail;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    private List<Authority> authorities;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonView(UserView.Manager.class)
    private Company company;

    @Transient
    @JsonView(UserView.Manager.class)
    private int workLoad;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_phones",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "phone_id")}
    )
    @JsonView(UserView.Manager.class)
    private List<Phone> phones = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<JobStatus> jobStatuses = new ArrayList<>();

    @Column(name = "enabled", columnDefinition = "Boolean default true")
    private Boolean enabled;


    @Column
    private String role;

    public User() {
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getWorkLoad() {
        return workLoad;
    }

    public void setWorkLoad(int workLoad) {
        this.workLoad = workLoad;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<JobStatus> getJobStatuses() {
        return jobStatuses;
    }

    public void setJobStatuses(List<JobStatus> jobStatuses) {
        this.jobStatuses = jobStatuses;
    }

}
