package com.webchat.webchat_server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webchat.webchat_server.entity.UserEntity;
import com.webchat.webchat_server.entity.UserInfoEntity;
import com.webchat.webchat_server.mapper.UserMapper;
import com.webchat.webchat_server.service.UserService;
import com.webchat.webchat_server.type.ServiceState;

import java.util.regex.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    private static final Pattern usernamePtn = Pattern.compile("^[a-zA-Z]\\w{3,}$");
    // private static final Pattern passwordPtn =
    // Pattern.compile("^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?!([^(0-9a-zA-Z)])+$).{6,20}$");

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
        if (!user.getPassword().equals(password))
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
    public int getUserId(String username){
        UserEntity user = userMapper.findUserByUsername(username);
        if(user==null)
            return -1;
        return user.getId();
    }
    //==========================================================

    public UserInfoEntity getUserInfo(int id) {
        // 可能为空值
        return userMapper.findUserInfoByUserId(id);
    }

    public UserInfoEntity getUserInfo(String username) {
        // 可能为空值
        UserEntity user = userMapper.findUserByUsername(username);
        if (user == null)
            return null;
        return userMapper.findUserInfoByUserId(user.getId());
    }

    /* 
    关于updateUserInfo的问题
    由于UserInfoEntity中包含自动增长的主键、绑定User的外键、自动更新的时间
    这些属性的存在导致直接映射不合适
    */

    public ServiceState updateUserInfo(int userId, UserInfoEntity newInfo) {
        UserEntity user = userMapper.findUserById(userId);
        UserInfoEntity userInfo = userMapper.findUserInfoByUserId(userId);
        if(user==null)
            return ServiceState.UNKNOWN_USER;
        if (userInfo == null){
            // User存在，UserInfo不存在，插入新记录
            if(!userMapper.addUserInfo(userId, newInfo))
                return ServiceState.DATABASE_ERROR;
        }else{
            // User存在，UserInfo也存在，进行更新
            if(!userMapper.updateUserInfo(userInfo.getId(), newInfo))
                return ServiceState.DATABASE_ERROR;
        }
        return ServiceState.SUCCESS;
    }
    
    public ServiceState updateUserInfo(String username, UserInfoEntity newInfo){
        UserEntity user = userMapper.findUserByUsername(username);
        return updateUserInfo(user.getId(), newInfo);
    }

}
