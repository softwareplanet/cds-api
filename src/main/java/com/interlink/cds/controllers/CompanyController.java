package com.interlink.cds.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.interlink.cds.components.CurrentCompany;
import com.interlink.cds.entities.Company;
import com.interlink.cds.entities.User;
import com.interlink.cds.exception.HttpException;
import com.interlink.cds.jsonView.CompanyView;
import com.interlink.cds.jsonView.UserView;
import com.interlink.cds.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
@CrossOrigin(origins = "*")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Autowired
    CurrentCompany currentCompany;

    @PostMapping("")
    @JsonView(UserView.Worker.class)
    public ResponseEntity createCompany( @RequestBody User user) {
        if(user.getCompany() == null) throw new HttpException(HttpStatus.BAD_REQUEST, "Add all information to the company");
        User userWithCompany = companyService.saveCompany(user);
        return ResponseEntity.ok(userWithCompany);
    }

    @GetMapping("")
    @JsonView(CompanyView.CompanyDetails.class)
    public ResponseEntity getCompany(Authentication authentication) {
        Company company = companyService.getCompany(currentCompany.getCompanyId(authentication));
        return ResponseEntity.ok(company);
    }

    @PatchMapping("")
    @JsonView(CompanyView.CompanyDetails.class)
    public ResponseEntity updateCompany(Authentication authentication, @RequestBody Company company) {

        Company updatedCompany =
                companyService.updateCompany(company,
                currentCompany.getCompanyId(authentication));

        return ResponseEntity.ok(updatedCompany);
    }
}
