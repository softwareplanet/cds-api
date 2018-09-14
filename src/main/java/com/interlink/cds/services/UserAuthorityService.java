package com.interlink.cds.services;

import com.interlink.cds.entities.Authority;
import com.interlink.cds.entities.User;
import com.interlink.cds.entities.authorityName.AuthorityName;
import com.interlink.cds.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserAuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    public void setAuthorities(User user, AuthorityName ... authorityNames) {
        Set<AuthorityName> authority= new HashSet<>(Arrays.asList(authorityNames));
        List<Authority> authorities = authorityRepository.findByNameIn(authority);
        user.setAuthorities(authorities);
    }

}
