package com.webchat.webchat_server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import com.webchat.webchat_server.entity.UserEntity;
import com.webchat.webchat_server.mapper.UserMapper;
import com.webchat.webchat_server.service.UserService;

@Service
public class UserServiceImpl implements UserService  {
    @Autowired
    private UserMapper userMapper;


}
