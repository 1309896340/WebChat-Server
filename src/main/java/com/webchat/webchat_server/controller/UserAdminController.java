package com.webchat.webchat_server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.webchat.webchat_server.service.UserService;
import com.webchat.webchat_server.type.ServiceState;
import com.webchat.webchat_server.type.UserAdminMessage;

// import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(value = "http://127.0.0.1:5173", allowedHeaders = "*", allowCredentials = "true")
public class UserAdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(String key, String value, HttpSession session) {
        // 测试session

        
        return "hello";
    }

    @PostMapping("/api/login")
    public String login(String username, String password, HttpSession session) {
        UserAdminMessage msg = new UserAdminMessage();
        if (userService.check(username, password) == ServiceState.SUCCESS) {
            msg.message = "登录成功";
            msg.status = true;
            // 记录session
            session.setAttribute("username", username);
            session.setAttribute("password", password);
        } else {
            msg.message = "登录失败";
            msg.status = false;
        }
        return JSON.toJSONString(msg);
    }

    @PostMapping("/api/register")
    public String register(String username, String password) {
        UserAdminMessage msg = new UserAdminMessage();
        if (userService.register(username, password) == ServiceState.SUCCESS) {
            msg.message = "注册成功";
            msg.status = true;
        } else {
            msg.message = "注册失败";
            msg.status = false;
        }
        return JSON.toJSONString(msg);
    }

}
