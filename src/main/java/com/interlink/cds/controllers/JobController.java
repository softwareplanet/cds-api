package com.interlink.cds.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.interlink.cds.components.CurrentCompany;

import com.interlink.cds.entities.Job;
import com.interlink.cds.exception.HttpException;
import com.interlink.cds.jsonView.JobView;
import com.interlink.cds.services.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    @Autowired
    JobService jobService;

    @Autowired
    CurrentCompany currentCompany;

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @JsonView(JobView.Manager.class)
    public ResponseEntity createJob(@Valid @RequestBody Job job, Authentication authentication) {
        Long companyId = currentCompany.getCompanyId(authentication);
        Job createdJob = jobService.createJob(companyId, job);
        return ResponseEntity.ok(createdJob);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @JsonView(JobView.AssigneeJob.class)
    public ResponseEntity getJobs(Authentication authentication) {
        Long companyId = currentCompany.getCompanyId(authentication);
        List<Job> jobs = jobService.getLessThanDueDateJobs(companyId);
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/{jobId}")
    @JsonView(JobView.AssigneeJob.class)
    public ResponseEntity getJobById(@PathVariable Long jobId) {
        Job job = jobService.getJobById(jobId);

        if(job == null) throw new HttpException(HttpStatus.NOT_FOUND, "Job is not found");

        return ResponseEntity.ok(job);
    }

    @GetMapping("/assignee/{assigneeId}")
    @JsonView({JobView.AssigneeJob.class})
    public ResponseEntity getJobsForAssignee(Authentication authentication, @PathVariable Long assigneeId){
        Long companyId = currentCompany.getCompanyId(authentication);
        List<Job> jobs = jobService.getAssigneeJobs(assigneeId, companyId);
        return ResponseEntity.ok().body(jobs);
    }

    @GetMapping("/status/{statusId}")
    @JsonView({JobView.AssigneeJob.class})
    public ResponseEntity getJobsByStatus(@PathVariable Long statusId,
                                          Authentication authentication) {
        Long companyId = currentCompany.getCompanyId(authentication);
        List jobs = jobService.getJobsByStatusId(statusId, companyId);
        return ResponseEntity.ok().body(jobs);
    }
}
