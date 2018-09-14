package com.interlink.cds.services;

import com.interlink.cds.entities.*;
import com.interlink.cds.exception.HttpException;
import com.interlink.cds.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatusRepository statusRepository;

    public Job createJob(Long companyId, Job job) {
        Job createdJob = jobRepository.save(job);
        Company company = companyRepository.findOne(companyId);
        configureJob(company, createdJob);
        return createdJob;
    }

    private void configureJob(Company company, Job createdJob) {
        Status status = getDefaultStatus(company);
        createdJob.setStatus(status);
        createdJob.setCompany(company);
        createdJob.setCreatedDate(new Date());
    }

    private Status getDefaultStatus(Company company) {
        Status defaultStatus = company.getDefaultStatus();
        if (defaultStatus == null) throw new HttpException(HttpStatus.NOT_FOUND, "Add default status to the company");
        Long statusId = defaultStatus.getId();
        return statusRepository.findOne(statusId);
    }

    public List<Job> getLessThanDueDateJobs(Long companyId) {
        List<Job> jobs = jobRepository.findAllByCompany_Id(companyId);
        return jobs;
    }

    public Job appointJob(Long jobId, Long assigneeId, Long companyId) {
        User assignee = getAssignee(assigneeId);
        Job job = jobRepository.findByIdAndCompany_Id(jobId, companyId);
        job.setAssignee(assignee);
        jobRepository.save(job);
        return job;
    }

    public Job getJobById(Long jobId) {
        Job job = jobRepository.findOne(jobId);
        job.getCompany();
        return job;
    }

    public Job changeJobStatus(Status newStatus, Long jobId) {
        Job job = jobRepository.findOne(jobId);
        Status status = statusRepository.getOne(newStatus.getId());
        job.setStatus(status);
        jobRepository.save(job);
        return job;
    }

    public List<Job> getAssigneeJobs(Long assigneeId, Long companyId) {
        List<Job> jobs = jobRepository.findAllByAssignee_idAndDueDateGreaterThanEqualAndCompany_Id(assigneeId, new Date(), companyId);
        return jobs;
    }

    public List<Job> getQueriedJobs(Specification<Job> spec){
        return  jobRepository.findAll(spec);
    }

    public List<Job> getJobsByStatusId(Long statusId, Long companyId){
        List<Job> jobs = jobRepository.findAllByCompany_IdAndStatus_Id(companyId, statusId);
        return jobs;
    }
    private User getAssignee(Long assigneeId) {
        return userRepository.findOne(assigneeId);
    }
}
