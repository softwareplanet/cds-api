package com.interlink.cds.services;

import com.interlink.cds.components.PasswordEncoder;
import com.interlink.cds.entities.Company;
import com.interlink.cds.entities.Status;
import com.interlink.cds.entities.User;
import com.interlink.cds.entities.authorityName.AuthorityName;
import com.interlink.cds.repository.AuthorityRepository;
import com.interlink.cds.repository.CompanyRepository;
import com.interlink.cds.repository.StatusRepository;
import com.interlink.cds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserAuthorityService userAuthorityService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    StatusRepository statusRepository;

    public User saveCompany(User user) {
        persistCompany(user);
        encodePassword(user);
        User createdUserWithCompany = userRepository.save(user);
        setAccess(user);
        setAuthorities(createdUserWithCompany);
        return createdUserWithCompany;
    }

    private void encodePassword(User user) {
        passwordEncoder.encodePassword(user);
    }

    private void setAuthorities(User createdUserWithCompany) {
        userAuthorityService.setAuthorities(createdUserWithCompany, AuthorityName.ROLE_ADMIN, AuthorityName.ROLE_USER);
    }

    private void setAccess(User user) {
        user.setEnabled(true);
    }

    public Company getCompany(Long id){
        return companyRepository.findOne(id);
    }

    private void persistCompany(User user) {
        Status status = statusRepository.save(user.getCompany().getDefaultStatus());
        Company company = companyRepository.save(user.getCompany());
        status.setCompany(company);

    }

    public Company updateCompany(Company company, Long companyId) {
        Company updatedCompany = companyRepository.findOne(companyId);
        company.setId(updatedCompany.getId());
        updatedCompany = company;
        companyRepository.save(updatedCompany);

        return updatedCompany;
    }
}
