package com.example.jwtdemo.config;

import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * @author Admin
 */

public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token)
    {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
