package com.webchat.webchat_server.service;

import java.sql.Timestamp;

public interface UserService {
    public boolean register(String username, String password);
    public boolean exist(String username);
    public boolean check(String username, String passowrd);
    public boolean updatePassword(String username, String oldPassword, String newPassword);
    public Timestamp getCreateTime(String username);
}
