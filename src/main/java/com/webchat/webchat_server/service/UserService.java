package com.webchat.webchat_server.service;

import com.webchat.webchat_server.entity.UserInfo;
import com.webchat.webchat_server.type.ServiceState;

public interface UserService {
    public ServiceState register(String username, String password);
    public ServiceState exist(String username);
    public ServiceState check(String username, String password);
    public ServiceState updatePassword(String username, String oldPassword, String newPassword);

    public UserInfo getUserInfo(int userId);
    public UserInfo getUserInfo(String username);
    public ServiceState updateUserInfo(int userId, UserInfo newInfo);
}
