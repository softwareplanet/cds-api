package com.interlink.cds.services;

import com.interlink.cds.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhoneService {

    @Autowired
    PhoneRepository phoneRepository;
}
