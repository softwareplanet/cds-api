package com.interlink.cds.controllers;

import com.fasterxml.jackson.annotation.JsonView;

import com.interlink.cds.components.CurrentCompany;
import com.interlink.cds.entities.Status;
import com.interlink.cds.jsonView.StatusView;
import com.interlink.cds.services.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("statuses")
@CrossOrigin(origins = "*")
public class StatusController {

    @Autowired
    StatusService statusService;

    @Autowired
    CurrentCompany currentCompany;

    @PostMapping("")
    @JsonView(StatusView.Worker.class)
    public ResponseEntity createStatus(@Valid @RequestBody Status newStatus, Authentication authentication) {
        Long companyId = currentCompany.getCompanyId(authentication);
        Status status = statusService.createStatus(companyId, newStatus);
        return ResponseEntity.ok(status);
    }

    @GetMapping("")
    @JsonView(StatusView.Worker.class)
    public ResponseEntity getStatuses(Authentication authentication) {
        Long companyId = currentCompany.getCompanyId(authentication);
        List<Status> statuses = statusService.getStatuses(companyId);

        return ResponseEntity.ok().body(statuses);
    }

}
