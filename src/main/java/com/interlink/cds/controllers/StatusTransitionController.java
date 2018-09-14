package com.interlink.cds.controllers;


import com.fasterxml.jackson.annotation.JsonView;

import com.interlink.cds.components.CurrentCompany;
import com.interlink.cds.entities.Company;
import com.interlink.cds.entities.StatusTransition;
import com.interlink.cds.jsonView.StatusView;
import com.interlink.cds.services.CompanyService;
import com.interlink.cds.services.StatusService;
import com.interlink.cds.services.StatusTransitionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("statuses/transitions")
@CrossOrigin(origins = "*")
public class StatusTransitionController {

    @Autowired
    StatusTransitionService statusTransitionService;

    @Autowired
    StatusService statusService;

    @Autowired
    CurrentCompany currentCompany;

    @Autowired
    CompanyService companyService;

    @GetMapping("")
    @JsonView(StatusView.Transition.class)
    public ResponseEntity getStatusTransition(Authentication authentication) {
        Long companyId = currentCompany.getCompanyId(authentication);
        List<StatusTransition> statuses = statusTransitionService.getStatusesTransition(companyId);
        return ResponseEntity.ok(statuses);
    }

    @PostMapping("")
    @JsonView(StatusView.Transition.class)
    public ResponseEntity createStatusesFlow(@Valid @RequestBody List<StatusTransition> transitions, Authentication authentication, @RequestParam(value="from") Long fromId) {
        Long companyId = currentCompany.getCompanyId(authentication);
        setStatusTransitionFlowForCompany(transitions, companyId);
        List<StatusTransition> createdTransitions = statusTransitionService.saveStatusTransitions(transitions, fromId);
        return ResponseEntity.ok().body(createdTransitions);
    }

    private void setStatusTransitionFlowForCompany(List<StatusTransition> transitions, Long companyId) {
        Company company = companyService.getCompany(companyId);
        for (int i = 0; i < transitions.size(); i++) {
            transitions.get(i).setCompany(company);
        }
    }

}
