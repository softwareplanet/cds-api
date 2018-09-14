package com.interlink.cds.controllers;

import com.fasterxml.jackson.annotation.JsonView;

import com.interlink.cds.entities.Job;
import com.interlink.cds.entities.Status;
import com.interlink.cds.exception.HttpException;
import com.interlink.cds.jsonView.JobView;
import com.interlink.cds.services.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs/{jobId}")
@CrossOrigin(origins = "*")
public class JobStatusController {

    @Autowired
    JobService jobService;

    @PatchMapping("/status")
    @JsonView(JobView.AssigneeJob.class)
    public ResponseEntity changeJobStatus(@PathVariable Long jobId, @RequestBody Status newStatus) {
        if(newStatus.getId() == null) throw new HttpException(HttpStatus.BAD_REQUEST, "Choose existing status");
        Job jobWithNewStatus = jobService.changeJobStatus(newStatus, jobId);
        return ResponseEntity.ok(jobWithNewStatus);
    }



}
