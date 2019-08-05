package com.example.jwtdemo.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Admin
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ExpiredJwtException.class)
    public String ExpiredJwtExceptionHandler()
    {
        return "签发的token过期，请重新登录！";
    }
}
