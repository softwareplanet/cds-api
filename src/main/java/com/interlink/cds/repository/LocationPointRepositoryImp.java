package com.interlink.cds.repository;

import com.interlink.cds.entities.LocationPoint;
import com.interlink.cds.entities.lastWorkerPositon.LastWorkerPosition;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LocationPointRepositoryImp {

    @PersistenceContext
    EntityManager entityManager;

    public List<LastWorkerPosition> getLastLocationPoint(Long id) {
        Query nativeQuery = entityManager.createNativeQuery(
                "select longitude, latitude, date, u.username " +
                        "from location_point lp " +
                        "inner join users u on lp.user_id = u.id " +
                        "where lp.id = ( select lp1.id " +
                        "from location_point lp1 " +
                        "inner join users u1 on lp1.user_id = u1.id " +
                        "where lp1.user_id = u.id and u.company_id = ?0 " +
                        "order by (date) desc limit 1)");
        nativeQuery.setParameter(0, id);
        List<Object[]> queryResultList = nativeQuery.getResultList();
        List<LastWorkerPosition> lastWorkerPositions = new ArrayList<>();
        queryResultList.stream().forEach((record) -> {
            Double lat = ((Double) record[0]).doubleValue();
            Double lon = ((Double) record[1]).doubleValue();
            LocalDateTime date = ((Timestamp) record[2]).toLocalDateTime();
            String version = (String) record[3];
            LastWorkerPosition lastWorkerPosition = new LastWorkerPosition(lat, lon, date, version);
            lastWorkerPositions.add(lastWorkerPosition);
        });
        return lastWorkerPositions;
    }
}
