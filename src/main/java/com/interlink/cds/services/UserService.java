package com.interlink.cds.services;

import com.interlink.cds.components.PasswordEncoder;
import com.interlink.cds.entities.Authority;
import com.interlink.cds.entities.Company;
import com.interlink.cds.entities.User;
import com.interlink.cds.entities.authorityName.AuthorityName;
import com.interlink.cds.repository.AuthorityRepository;
import com.interlink.cds.repository.CompanyRepository;
import com.interlink.cds.repository.JobRepository;
import com.interlink.cds.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserAuthorityService userAuthorityService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> getAllWorkers(Long companyId) {
        List<Authority> authoritiesNotApplyToTheWorker = getAuthoritiesNotApplyToTheWorker();
        List<User> workers = userRepository.findAllByCompanyIdAndAuthoritiesIsNotContaining(companyId, authoritiesNotApplyToTheWorker);
        setWorkLoad(workers);
        return workers;
    }

    private void setWorkLoad(List<User> workers) {
        for (User worker : workers) {
            Long workerId = worker.getId();
            int userWorkload = getUserWorkload(workerId);
            worker.setWorkLoad(userWorkload);
        }
    }

    private int getUserWorkload(Long userId) {
        User user = userRepository.findOne(userId);
        return jobRepository.findByAssignee(user).size();
    }

    private List<Authority> getAuthoritiesNotApplyToTheWorker() {
        Set<AuthorityName> authorityNames = new HashSet<>();
        authorityNames.add(AuthorityName.ROLE_ADMIN);
        return authorityRepository.findByNameIn(authorityNames);
    }

    public User createWorker(User user, Long companyId) {
        Company company = companyRepository.findOne(companyId);
        passwordEncoder.encodePassword(user);
        user.setCompany(company);
        User createdUserWithCompany = userRepository.save(user);
        userAuthorityService.setAuthorities(createdUserWithCompany, AuthorityName.ROLE_USER);

        return userRepository.save(user);
    }

}
