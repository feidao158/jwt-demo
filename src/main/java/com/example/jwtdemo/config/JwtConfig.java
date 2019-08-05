package com.example.jwtdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Admin
 */
@Configuration
@ConfigurationProperties(prefix = "jwt")
@PropertySource("jwt.properties")
public class JwtConfig {

    private String exp;

    private String security;

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }
}
