package com.interlink.cds.services;

import com.interlink.cds.entities.LocationPoint;
import com.interlink.cds.entities.User;
import com.interlink.cds.entities.lastWorkerPositon.LastWorkerPosition;
import com.interlink.cds.exception.HttpException;
import com.interlink.cds.repository.LocationPointRepository;
import com.interlink.cds.repository.LocationPointRepositoryImp;
import com.interlink.cds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.Temporal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LocationPointService {

    @Autowired
    LocationPointRepository locationPointRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationPointRepositoryImp locationPointRepositoryImp;

    public List<LocationPoint> getAssigneeLocationPoints(Long companyId, Long userId, LocalDate date) {
        LocalDateTime startOfADay = date.atStartOfDay();
        LocalDateTime endOfADay = date.atStartOfDay().plusDays(1);

        return locationPointRepository.findByUser_IdAndUser_Company_IdAndDateBetween(userId, companyId, startOfADay, endOfADay);
    }

    public LocationPoint saveLocationPoint(LocationPoint locationPoint, Long assigneeId) {
        User user = getUserById(assigneeId);
        locationPoint.setUser(user);
        LocationPoint createdLocationPoint = locationPointRepository.save(locationPoint);
        return createdLocationPoint;
    }

    private User getUserById(Long assigneeId) {
        User user = userRepository.getOne(assigneeId);
        if (user == null) throw new HttpException(HttpStatus.NOT_FOUND, "User not found");
        return user;
    }

    public List<LastWorkerPosition> getLatestLocationPoints(Long id ){
        return  locationPointRepositoryImp.getLastLocationPoint(id);
    }
}
