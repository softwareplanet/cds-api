package com.interlink.cds.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.interlink.cds.jsonView.StatusView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "status_transition")
public class StatusTransition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(StatusView.Transition.class)
    private Long id;

    @Column
    @NotNull
    @JsonView(StatusView.Transition.class)
    private String action;

    @OneToOne
    @NotNull
    @JoinColumn(name = "status_from")
    @JsonView(StatusView.Transition.class)
    private Status statusFrom;

    @OneToOne
    @NotNull
    @JoinColumn(name = "status_to")
    @JsonView(StatusView.Transition.class)
    private Status statusTo;

    @ManyToOne
    private Company company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Status getStatusFrom() {
        return statusFrom;
    }

    public void setStatusFrom(Status statusesFrom) {
        this.statusFrom = statusesFrom;
    }

    public Status getStatusesTo() {
        return statusTo;
    }

    public void setStatusesTo(Status statusesTo) {
        this.statusTo = statusesTo;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
