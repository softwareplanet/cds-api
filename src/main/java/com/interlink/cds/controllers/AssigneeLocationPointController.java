package com.interlink.cds.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.interlink.cds.components.CurrentCompany;
import com.interlink.cds.entities.LocationPoint;
import com.interlink.cds.entities.User;
import com.interlink.cds.entities.jwtEntities.JwtUser;
import com.interlink.cds.entities.lastWorkerPositon.LastWorkerPosition;
import com.interlink.cds.exception.HttpException;
import com.interlink.cds.jsonView.LocationPointView;
import com.interlink.cds.services.LocationPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("assignee")
@CrossOrigin(origins = "*")
public class AssigneeLocationPointController {

    @Autowired
    LocationPointService locationPointsService;

    @Autowired
    CurrentCompany currentCompany;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{assigneeId}/location")
    @JsonView(LocationPointView.Manager.class)
    public ResponseEntity getAssigneeLocationPoints(@PathVariable  Long assigneeId,
                                                    @RequestParam(required = true) String date,
                                                    Authentication authentication) {
        Long companyId = currentCompany.getCompanyId(authentication);
        LocalDate searchingDate = parseSearchingDate(date);
        List<LocationPoint> assigneeLocationPoints = getLocationPoints(assigneeId, companyId, searchingDate);
        return ResponseEntity.ok(assigneeLocationPoints);
    }

    private LocalDate parseSearchingDate(String date) {
        LocalDate searchingDate;

        try {
            searchingDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Invalid date format.");
        }
        return searchingDate;
    }

    private List<LocationPoint> getLocationPoints(Long assigneeId, Long companyId, LocalDate searchingDate) {
        List<LocationPoint> assigneeLocationPoints = locationPointsService.getAssigneeLocationPoints(companyId, assigneeId, searchingDate);

        if (assigneeLocationPoints.isEmpty())
            throw new HttpException(HttpStatus.NOT_FOUND, "Can't find any location points.");
        return assigneeLocationPoints;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/location")
    public ResponseEntity saveLocationPoint(@RequestBody LocationPoint locationPoint, Authentication authentication) {
        JwtUser assignee = (JwtUser) authentication.getPrincipal();
        locationPointsService.saveLocationPoint(locationPoint, assignee.getId());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/location/last")
    public ResponseEntity getLocationPoint(Authentication authentication) {
        Long companyId = currentCompany.getCompanyId(authentication);
        List<LastWorkerPosition> locationPoints = locationPointsService.getLatestLocationPoints(companyId);
        return ResponseEntity.ok(locationPoints);
    }




}
