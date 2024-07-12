package com.webchat.webchat_server.mapper;

import org.apache.ibatis.annotations.*;
import com.webchat.webchat_server.entity.UserEntity;
import com.webchat.webchat_server.entity.UserInfo;

@Mapper
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    public UserEntity findUserById(int id);

    @Select("select * from user where username=#{username}")
    public UserEntity findUserByUsername(String username);

    @Insert("insert into user (username, password) values (#{username},#{password})")
    public boolean addUser(String username, String password);

    @Update("update user set password=#{newPassword} where username=#{username}")
    public boolean updateUserPassword(String username, String newPassword);

    @Delete("delete from user where id=#{id}")
    public boolean removeUserById(int id);

    @Delete("delete from user where id=#{id}")
    public boolean removeUserByUsername(String username);

    
    @Insert("insert into userinfo (uid, nickname, sex, birth, email, qq, wechat) values (#{uid}, #{nickname}, #{sex}, #{birth}, #{email}, #{qq}, #{wechat})")
    public boolean addUserInfo(UserInfo userInfo, int id);

    @Select("select * from userinfo where uid=#{uid}")
    public UserInfo findUserInfoById(int id);

}
