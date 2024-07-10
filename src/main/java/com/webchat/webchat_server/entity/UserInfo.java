package com.webchat.webchat_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

enum Sex{
    male, female
}

@Data
@AllArgsConstructor
@Getter
@Setter
public class UserInfo {
    private int id;
    private int uid;
    private String nickname;
    private Sex sex;
    private Date birth;
    private String email;
    private String qq;
    private String wechat;
    private Date updateTime;
}
