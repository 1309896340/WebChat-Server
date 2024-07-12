package com.webchat.webchat_server.service;

import com.webchat.webchat_server.entity.UserInfoEntity;
import com.webchat.webchat_server.type.ServiceState;

public interface UserService {
    public ServiceState register(String username, String password);
    public ServiceState exist(String username);
    public ServiceState check(String username, String password);
    public ServiceState updatePassword(String username, String oldPassword, String newPassword);
    public int getUserId(String username);
    // public UserEntity getUser(int id);

    public UserInfoEntity getUserInfo(int userId);
    public UserInfoEntity getUserInfo(String username);
    public ServiceState updateUserInfo(int userId, UserInfoEntity newInfo);
    public ServiceState updateUserInfo(String username, UserInfoEntity newInfo);
}
