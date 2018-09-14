package com.interlink.cds.components;

import com.interlink.cds.entities.jwtEntities.JwtUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CurrentCompany {

    public Long getCompanyId(Authentication authentication) {
        JwtUser user = (JwtUser) authentication.getPrincipal();
        return user.getCompanyId();
    }

}
