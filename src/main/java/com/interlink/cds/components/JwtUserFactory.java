package com.interlink.cds.components;

import com.interlink.cds.entities.Authority;
import com.interlink.cds.entities.User;
import com.interlink.cds.entities.jwtEntities.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.geteMail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getAuthorities()),
                user.getEnabled(),
                user.getCompany().getId()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}
