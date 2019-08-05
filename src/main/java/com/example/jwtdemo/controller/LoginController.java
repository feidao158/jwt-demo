package com.example.jwtdemo.controller;

import com.example.jwtdemo.config.JwtToken;
import com.example.jwtdemo.pojo.JsonResult;
import com.example.jwtdemo.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
/**
 * @author Admin
 */
@Controller
public class LoginController {

    @PostMapping("/login")
    @ResponseBody
    public JsonResult login(String username, String password)
    {
        JsonResult jsonResult = new JsonResult();

        if("zhangwei".equals(username) && "abc-123".equals(password))
        {
            String token = JwtUtils.generateJwt("1", "zhangwei", "admin");
            jsonResult.setStatus(200);
            jsonResult.setMsg("登录成功");
            HashMap<String, String> dataInfo = new HashMap<>(16);
            dataInfo.put("token",token);
            jsonResult.setData(dataInfo);
            return jsonResult;
        }
        jsonResult.setStatus(403);
        jsonResult.setMsg("账号或密码错误");
        return jsonResult;

    }
}
