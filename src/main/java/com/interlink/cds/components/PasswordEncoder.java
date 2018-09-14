package com.interlink.cds.components;

import com.interlink.cds.entities.User;
import com.interlink.cds.exception.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    public String encodePassword(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String password = user.getPassword();
        String passwordHash = bCryptPasswordEncoder.encode(password);
        
        if(!bCryptPasswordEncoder.matches(password, passwordHash)) throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, "Saving password error");

        user.setPassword(passwordHash);

        return passwordHash;
    }
}
