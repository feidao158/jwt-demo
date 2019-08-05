package com.example.jwtdemo.controller;

import com.example.jwtdemo.config.JwtConfig;
import com.example.jwtdemo.pojo.JsonResult;
import com.example.jwtdemo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Admin
 */
@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    JwtConfig jwtConfig;

    @GetMapping("/info")
    public String getInfo()
    {
        return JwtUtils.generateJwt("111","zhangwei",null);
    }

    @GetMapping("/parse")
    public JsonResult parseUserInfo(HttpServletRequest request)
    {
        JsonResult jsonResult = new JsonResult();
        String authorization = request.getHeader("Authorization");
        Claims claims = JwtUtils.parseJwt(authorization);
        jsonResult.setStatus(200);
        jsonResult.setMsg(claims.getId() + "\n" + claims.getSubject() +"\n"  +claims.get("role"));
        return jsonResult;
    }
}
