package com.webchat.webchat_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

enum Sex{
    unknown(0), male(1), female(2);

    private final int id;
    Sex(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}

@Data
@AllArgsConstructor
@Getter
@Setter
public class UserInfoEntity {
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
