package com.example.jwtdemo.config;

import com.example.jwtdemo.filter.JwtFilter;
import com.example.jwtdemo.service.JwtRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;

@Configuration
public class ShiroConfig {

    @Bean
    public JwtRealm jwtRealm()
    {
        return new JwtRealm();
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(JwtRealm jwtRealm)
    {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(jwtRealm);
        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("/js/**","anon");
        hashMap.put("/403.html","anon");
        hashMap.put("/admin/index.html","anon");
        hashMap.put("/admin/**","jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(hashMap);
        HashMap<String, Filter> stringFilterHashMap = new HashMap<>();
        stringFilterHashMap.put("jwt",new JwtFilter());
        shiroFilterFactoryBean.setFilters(stringFilterHashMap);
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        return shiroFilterFactoryBean;
    }

}
