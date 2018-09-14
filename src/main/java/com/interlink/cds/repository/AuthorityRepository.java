package com.interlink.cds.repository;

import com.interlink.cds.entities.Authority;
import com.interlink.cds.entities.authorityName.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findByNameIn(Set<AuthorityName> roles);
}
