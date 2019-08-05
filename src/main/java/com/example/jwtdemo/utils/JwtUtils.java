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
import java.util.Iterator;
import java.util.Map;

/**
 * @author Admin
 */
@Component
public class JwtUtils {

    @Autowired
    JwtConfig jwtConfigInfo;

    static JwtConfig jwtConfig;

    public static String getExp() {
        return jwtConfig.getExp();
    }

    @PostConstruct
    public void init() {
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
     *
     * @param id 用户唯一id
     * @param subject 当前认证主体
     * @param map 其他参数信息
     * @return
     */
    public static String generateJwt(String id, String subject, Map<String, String> map) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Integer.parseInt(jwtConfig.getExp())))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecurity());
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,String> entry = (Map.Entry)iterator.next();
            jwtBuilder.claim(entry.getKey(),entry.getValue());
        }
        return jwtBuilder.compact();
    }

    /**
     * @param token token信息
     * @return
     */
    public static Claims parseJwt(String token) {
        Claims body = Jwts.parser().setSigningKey(jwtConfig.getSecurity())
                .parseClaimsJws(token)
                .getBody();
        return body;
    }

}
