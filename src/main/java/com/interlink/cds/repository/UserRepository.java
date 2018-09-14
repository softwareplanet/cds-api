package com.interlink.cds.repository;

import com.interlink.cds.entities.Authority;
import com.interlink.cds.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRoleAndCompany_Id(String role, Long companyId );
    User findByUsername(String username);
    User findByUsernameAndCompany_Id(String username, Long companyId);
    List<User> findAllByCompanyIdAndAuthoritiesIsNotContaining(Long companyId, List<Authority> roles);
}
