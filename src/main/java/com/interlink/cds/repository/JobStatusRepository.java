package com.interlink.cds.repository;

import com.interlink.cds.entities.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobStatusRepository extends JpaRepository<JobStatus, Long> {
}
