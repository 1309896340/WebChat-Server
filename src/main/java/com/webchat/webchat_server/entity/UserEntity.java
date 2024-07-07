package com.webchat.webchat_server.entity;

import java.sql.Timestamp;
import com.alibaba.fastjson.JSON;
import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    private int id;
    private String username;
    private String password;
    private Timestamp createTime;
    private Timestamp updateTime;

    public String toString(){
        return JSON.toJSONString(this);
    }
}
