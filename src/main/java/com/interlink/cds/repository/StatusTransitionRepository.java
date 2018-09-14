package com.interlink.cds.repository;

import com.interlink.cds.entities.StatusTransition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusTransitionRepository extends JpaRepository<StatusTransition, Long> {
    List<StatusTransition> findAllByCompany_Id(Long companyId);
    List<StatusTransition> findAllByStatusFrom_Id(Long id);
}
