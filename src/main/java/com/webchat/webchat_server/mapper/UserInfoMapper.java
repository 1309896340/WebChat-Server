package com.webchat.webchat_server.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.webchat.webchat_server.entity.UserInfo;

@Mapper
public interface UserInfoMapper {
    @Insert("insert into userinfo (nickname, sex, birth, email, qq, wechat) values (#{nickname},#{sex},#{birth},#{email},#{qq},#{wechat})")
    public boolean addUserInfoAssociateToUserId(UserInfo userInfo, int uid);

    @Select("select * from userinfo where uid=#{uid}")
    public UserInfo findUserInfoById(int uid);

    // 未完待续

}
