package com.interlink.cds.services;

import com.interlink.cds.repository.JobRepository;
import com.interlink.cds.repository.JobStatusRepository;
import com.interlink.cds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JobStatusService {

    @Autowired
    JobStatusRepository jobStatusRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JobRepository jobRepository;

}
