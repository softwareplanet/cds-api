package com.interlink.cds.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.interlink.cds.components.CurrentCompany;
import com.interlink.cds.entities.User;
import com.interlink.cds.jsonView.UserView;
import com.interlink.cds.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class WorkerController {

    @Autowired
    UserService userService;

    @Autowired
    CurrentCompany currentCompany;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @JsonView(UserView.Manager.class)
    public ResponseEntity getWorkers(Authentication authentication ) {
        Long companyId = currentCompany.getCompanyId(authentication);
        List<User> workers = userService.getAllWorkers(companyId);
        return ResponseEntity.ok(workers);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @JsonView(UserView.Manager.class)
    public User crateWorker(@Valid  @RequestBody User user, Authentication authentication){
        Long companyId = currentCompany.getCompanyId(authentication);
        User createdUser = userService.createWorker(user, companyId);
        return createdUser;
    }

}
