package com.interlink.cds.services;

import com.interlink.cds.entities.Status;
import com.interlink.cds.entities.StatusTransition;
import com.interlink.cds.repository.StatusRepository;
import com.interlink.cds.repository.StatusTransitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatusTransitionService {

    @Autowired
    StatusTransitionRepository statusTransitionRepository;

    @Autowired
    StatusRepository statusRepository;

    public List<StatusTransition> getStatusesTransition(Long companyId){
        return  statusTransitionRepository.findAllByCompany_Id(companyId);
    }

    public List<StatusTransition> saveStatusTransitions(List<StatusTransition> transitions, Long fromId) {
        deleteOldTransitions(fromId);
        for(StatusTransition statusTransition : transitions) {
            saveTransition(statusTransition);
        }
        return transitions;
    }

    private boolean transitionNotExist(StatusTransition statusTransition) {
        return statusTransition.getId() == null;
    }

    private StatusTransition saveTransition(StatusTransition statusTransition) {
        return statusTransitionRepository.save(statusTransition);
    }

    private void updateTransition(StatusTransition statusTransition) {
        StatusTransition temp = statusTransitionRepository.findOne(statusTransition.getId());
        temp.setAction(statusTransition.getAction());
        saveTransition(temp);
    }

    private void deleteOldTransitions(Long statusFromId) {
        List<StatusTransition> oldTransitions = statusTransitionRepository.findAllByStatusFrom_Id(statusFromId);
        statusTransitionRepository.delete(oldTransitions);
    }
}
