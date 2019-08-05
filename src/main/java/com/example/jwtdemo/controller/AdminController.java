package com.example.jwtdemo.controller;

import org.springframework.stereotype.Controller;
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
}
