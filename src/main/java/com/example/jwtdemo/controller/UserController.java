package com.example.jwtdemo.controller;

import com.example.jwtdemo.config.JwtConfig;
import com.example.jwtdemo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Admin
 */
@RestController
public class UserController {
    @Autowired
    JwtConfig jwtConfig;

    @GetMapping("/info")
    public String getInfo()
    {
        return JwtUtils.generateJwt("111","zhangwei","admin");
    }


    @GetMapping("/parse")
    public String parseUserInfo(String token)
    {
        Claims claims = JwtUtils.parseJwt(token);
        return claims.getId() + "\n" + claims.getSubject() +"\n"  +claims.get("role");
    }
}
