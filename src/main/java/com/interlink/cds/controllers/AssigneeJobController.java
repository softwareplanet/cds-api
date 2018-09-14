package com.interlink.cds.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.interlink.cds.components.CurrentCompany;
import com.interlink.cds.entities.Job;
import com.interlink.cds.entities.User;
import com.interlink.cds.entities.jwtEntities.JwtUser;
import com.interlink.cds.jsonView.JobView;
import com.interlink.cds.services.JobService;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@CrossOrigin(origins = "*")
public class AssigneeJobController {

    @Autowired
    JobService jobService;

    @Autowired
    CurrentCompany currentCompany;

    @PutMapping("/{jobId}/assignee")
    @JsonView(JobView.AssigneeJob.class)
    public ResponseEntity appointJobToWorker(@PathVariable Long jobId,
                                             @RequestBody User assignee,
                                             Authentication authentication) {
        Long companyId = currentCompany.getCompanyId(authentication);
        Long assigneeId = assignee.getId();
        Job job = jobService.appointJob(jobId, assigneeId, companyId);
        return ResponseEntity.ok(job);
    }

    @GetMapping("/assignee")
    @PreAuthorize("hasRole('ROLE_USER')")
    @JsonView(JobView.AssigneeJob.class)
    public ResponseEntity getAssigneeJobs(Authentication authentication) {
        Long companyId = currentCompany.getCompanyId(authentication);
        Long assigneeId = ((JwtUser) authentication.getPrincipal()).getId();
        List<Job> jobs = jobService.getAssigneeJobs(assigneeId, companyId);
        return ResponseEntity.ok(jobs);
    }



}
