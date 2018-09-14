package com.interlink.cds.services;

import com.interlink.cds.entities.Company;
import com.interlink.cds.entities.Status;
import com.interlink.cds.repository.CompanyRepository;
import com.interlink.cds.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;
    @Autowired
    CompanyRepository companyRepository;

    public List<Status> getStatuses(Long companyId) {
            return statusRepository.findByCompany_Id(companyId);
    }

    public Status createStatus(Long companyId, Status status) {
        Company company = companyRepository.findOne(companyId);
        status.setCompany(company);
        statusRepository.save(status);
        return status;
    }

}
