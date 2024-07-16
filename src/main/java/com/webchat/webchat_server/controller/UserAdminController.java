package com.webchat.webchat_server.controller;

import java.util.Enumeration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.webchat.webchat_server.entity.UserInfoEntity;
import com.webchat.webchat_server.service.UserService;
import com.webchat.webchat_server.type.ServiceState;
import com.webchat.webchat_server.type.UserAdminMessage;

import jakarta.servlet.http.HttpSession;

import com.webchat.webchat_server.type.MessageState;

@RestController
public class UserAdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(String key, String value, HttpSession session) {
        // 测试session

        return "hello";
    }


    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user, HttpSession session) {
        String username = user.get("username");
        String password = user.get("password");
        UserAdminMessage msg = new UserAdminMessage();
        if (userService.check(username, password) == ServiceState.SUCCESS) {
            msg.message = "登录成功";
            msg.status = MessageState.SUCCESS;
            // 记录session
            session.setAttribute("username", username);
            session.setAttribute("password", password);
        } else {
            msg.message = "登录失败";
            msg.status = MessageState.LOGIN_FAILURE;
        }
        return JSON.toJSONString(msg);
    }

    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> user) {
        String username = user.get("username");
        String password = user.get("password");
        UserAdminMessage msg = new UserAdminMessage();
        if (userService.register(username, password) == ServiceState.SUCCESS) {
            msg.message = "注册成功";
            msg.status = MessageState.SUCCESS;
        } else {
            msg.message = "注册失败";
            msg.status = MessageState.REGISTER_FAILURE;
        }
        return JSON.toJSONString(msg);
    }

    @GetMapping("/api/userinfo")
    public String getUserInfo(@SessionAttribute(name="username",required = false) String username) {
        UserAdminMessage msg = new UserAdminMessage();

        System.out.println("进入到/userinfo控制器\nusername = "+ username);

        if (username == null) {
            // session已经过期，需要重新登录，跳转到登录界面
            msg.status = MessageState.SESSION_EXPIRED;
            msg.message = "登录已过期，需要重新登录！";
            msg.data = "";
            return JSON.toJSONString(msg);
        }
        msg.status = MessageState.SUCCESS;
        UserInfoEntity userInfo = userService.getUserInfo(username);
        if (userInfo == null) {
            msg.message = "查询成功，但记录不存在.";
            msg.data = JSON
                    .toJSONString(new UserInfoEntity(0, 0, username, null, null, username, username, username, null));
            return JSON.toJSONString(msg);
        }

        msg.message = "查询成功，记录存在.";
        msg.data = JSON.toJSONString(userInfo);
        return JSON.toJSONString(msg);
    }

    @PostMapping("/userinfo")
    public String postMethodName(@RequestBody Map<String, String> newUserInfo, HttpSession session) {
        UserAdminMessage msg = new UserAdminMessage();
        String username = (String) session.getAttribute("username");
        if (username == null) {
            // session已经过期，需要重新登录，跳转到登录界面
            msg.status = MessageState.SESSION_EXPIRED;
            msg.message = "登录已过期，需要重新登录！";
            msg.data = "";
            return JSON.toJSONString(msg);
        }
        msg.status = MessageState.SUCCESS;
        int id = userService.getUserId(username);
        String sex = newUserInfo.get("sex");
        String birth = newUserInfo.get("birth"); // 需要查看
        System.out.println("测试controller层获取到的未知数据映射格式：\nsex = " + sex + "\nbirth = " + birth);
        String email = newUserInfo.get("email");
        String qq = newUserInfo.get("qq");
        String wechat = newUserInfo.get("wechat");
        UserInfoEntity userInfo = new UserInfoEntity(0, id, username, null, null, email, qq, wechat, null);
        if (userService.updateUserInfo(username, userInfo) != ServiceState.SUCCESS) {
            msg.status = MessageState.UNKNOWN_ERROR;
            msg.message = "操作失败，错误未知";
            msg.data = "";
            return JSON.toJSONString(msg);
        }
        msg.status = MessageState.SUCCESS;
        msg.message = "用户信息更新成功！返回更新后的用户信息";
        msg.data = JSON.toJSONString(userService.getUserInfo(username)); // 返回更新后的用户信息
        return JSON.toJSONString(msg);
    }

}
