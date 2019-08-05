package com.example.jwtdemo.controller;

import com.example.jwtdemo.pojo.JsonResult;
import com.example.jwtdemo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/index")
    @ResponseBody
    public void index(HttpServletResponse response)
    {
        try {
            response.sendRedirect("/admin/index.html");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    @GetMapping("/token/refresh")
    public JsonResult refreshToken(String token)
    {
        JsonResult jsonResult = new JsonResult();
        try {
            Claims claims = JwtUtils.parseJwt(token);
            jsonResult.setStatus(200);
            jsonResult.setMsg("success");
        }catch (RuntimeException e)
        {
            jsonResult.setStatus(505);
            jsonResult.setMsg("token出错");
        }
        return jsonResult;


    }
}
