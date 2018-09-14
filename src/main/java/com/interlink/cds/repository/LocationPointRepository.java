package com.interlink.cds.repository;

import com.interlink.cds.entities.LocationPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LocationPointRepository extends JpaRepository<LocationPoint, Long> {
    List<LocationPoint> findByUser_IdAndUser_Company_IdAndDateBetween(Long userId, Long companyId, LocalDateTime startDate, LocalDateTime endDate);

}

