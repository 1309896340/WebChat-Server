package com.webchat.webchat_server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webchat.webchat_server.entity.UserEntity;
import com.webchat.webchat_server.mapper.UserMapper;
import com.webchat.webchat_server.service.UserService;
import com.webchat.webchat_server.type.ServiceState;

import java.util.regex.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    private static final Pattern usernamePtn = Pattern.compile("^[a-zA-Z]\\w{3,}$");
    // private static final Pattern passwordPtn = Pattern.compile("^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?!([^(0-9a-zA-Z)])+$).{6,20}$");

    public ServiceState register(String username, String password) {
        // 对传进来的username和password要进行格式验证，对于非法的格式不予执行，并返回ServiceState.INVALID_FORMAT
        Matcher matcher = usernamePtn.matcher(username);
        if (!matcher.find())
            return ServiceState.INVALID_FORMAT;
        System.out.println("register success!  username: " + username + " password: " + password);
        // =========================================
        if (exist(username) == ServiceState.SUCCESS)
            return ServiceState.EXISTED_USER;
        if (!userMapper.addUser(username, password))
            return ServiceState.DATABASE_ERROR;
        return ServiceState.SUCCESS;
    }

    public ServiceState exist(String username) {
        UserEntity user = userMapper.findUserByUsername(username);
        if (user == null)
            return ServiceState.UNKNOWN_USER;
        return ServiceState.SUCCESS;
    }

    public ServiceState check(String username, String password) {
        UserEntity user = userMapper.findUserByUsername(username);
        if (user == null)
            return ServiceState.UNKNOWN_USER;
        if (user.getPassword().equals(password))
            return ServiceState.WRONG_PASSWORD;
        return ServiceState.SUCCESS;
    }

    public ServiceState updatePassword(String username, String oldPassword, String newPassword) {
        ServiceState flag = check(username, oldPassword);
        if (flag != ServiceState.SUCCESS)
            return flag;
        if (!userMapper.updateUserPassword(username, newPassword))
            return ServiceState.DATABASE_ERROR;
        return ServiceState.SUCCESS;
    }

}
