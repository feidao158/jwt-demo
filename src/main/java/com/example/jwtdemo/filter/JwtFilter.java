package com.example.jwtdemo.filter;

import com.example.jwtdemo.config.JwtToken;
import com.example.jwtdemo.pojo.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * preHandle->isAccessAllowed->isLoginAttempt->executeLogin
 * @author Admin
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {
    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String authorization = httpServletRequest.getHeader("Authorization");
        System.out.println(authorization);
        boolean empty = StringUtils.isEmpty(authorization);
        return !empty;
    }


    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");
        JwtToken jwtToken = new JwtToken(authorization);
        try {
            getSubject(request,response).login(jwtToken);
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;


//        如果没有出现异常 登录成功
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest res = (HttpServletRequest)request;
        String requestURI = res.getRequestURI();
        if (requestURI.endsWith(".html"))
        {
            return true;
        }
        if(isLoginAttempt(request,response))
        {
            try {
                executeLogin(request,response);
                System.out.println("我国立了");
            }catch (Exception e)
            {
                response401(request,response);
            }
        }
        responseIndex(response);
        return true;
    }

//    @Override
//    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//        System.out.printf("我是谁 我在哪5");
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
//        // 跨域时会首先发送一个option请求，这里给option请求直接返回正常状态
//        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
//            httpServletResponse.setStatus(HttpStatus.OK.value());
//            return false;
//        }
//        return super.preHandle(request, response);
//    }

    private void response401(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            httpServletResponse.sendRedirect("/login.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void responseIndex(ServletResponse response)
    {
        HttpServletResponse res = (HttpServletResponse)response;
        try {
            res.sendRedirect("/admin/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
