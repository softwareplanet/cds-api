package com.interlink.cds.repository;

import com.interlink.cds.entities.Job;
import com.interlink.cds.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
    Job findByIdAndCompany_Id(Long jobId, Long companyId);

    List<Job> findByAssignee(User user);
    List<Job> findAllByAssignee_idAndCompany_Id(Long assigneeId, Long companyId);
    List<Job> findAllByAssignee_idAndDueDateGreaterThanEqualAndCompany_Id(Long assigneeId, @Temporal(TemporalType.DATE) Date date, Long companyId);
    List<Job> findAllByCompany_Id(Long companyId);
    List<Job> findAllByCompany_IdAndStatus_Id(Long companyId, Long statusId);
}
