package com.interlink.cds.services;

import com.interlink.cds.components.JwtUserFactory;
import com.interlink.cds.entities.User;
import com.interlink.cds.exception.HttpException;
import com.interlink.cds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
//            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        throw new HttpException(HttpStatus.NOT_FOUND, "sorry");
        } else {
            return JwtUserFactory.create(user);
        }
    }

}
