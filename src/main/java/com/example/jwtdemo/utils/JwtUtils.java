package com.example.jwtdemo.utils;

import com.example.jwtdemo.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @author Admin
 */
@Component
public class JwtUtils {

    @Autowired
    JwtConfig jwtConfigInfo;

    static  JwtConfig jwtConfig;

    public static  String getExp()
    {
        return jwtConfig.getExp();
    }
    @PostConstruct
    public void init()
    {
        JwtUtils.setJwtConfig(jwtConfigInfo);
    }

    public JwtConfig getJwtConfigInfo() {
        return jwtConfigInfo;
    }

    public void setJwtConfigInfo(JwtConfig jwtConfigInfo) {
        this.jwtConfigInfo = jwtConfigInfo;
    }

    public static JwtConfig getJwtConfig() {
        return jwtConfig;
    }

    public static void setJwtConfig(JwtConfig jwtConfig) {
        JwtUtils.jwtConfig = jwtConfig;
    }


    /**
     * 生成jwt信息
     * @param id 唯一id标识
     * @param subject 当前认证主体
     * @param role 用户角色
     * @return
     */
    public static String generateJwt(String id,String subject,String role)
    {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .claim("role",role)
                .setExpiration(new Date(System.currentTimeMillis() + Integer.parseInt(jwtConfig.getExp())))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecurity());
        return jwtBuilder.compact();
    }

    /**
     *
     * @param token token信息
     * @return
     */
    public static Claims parseJwt(String token)
    {
        Claims body = Jwts.parser().setSigningKey(jwtConfig.getSecurity())
                .parseClaimsJws(token)
                .getBody();
        return body;
    }

}
