package com.example.jwtdemo.exception;

import com.example.jwtdemo.pojo.JsonResult;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Admin
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ExpiredJwtException.class)
    public JsonResult ExpiredJwtExceptionHandler(RuntimeException e)
    {
        System.out.println("token过期了");
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(505);
        jsonResult.setMsg("签发的token过期 请重新登陆");
        return jsonResult;
    }



}
