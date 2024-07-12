package com.webchat.webchat_server.mapper;

import org.apache.ibatis.annotations.*;
import com.webchat.webchat_server.entity.UserEntity;
import com.webchat.webchat_server.entity.UserInfoEntity;

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
    public boolean addUserInfo(int id, UserInfoEntity userInfo);

    @Update("""
        <script>
            UPDATE userinfo
            <set>
                uid = #{userinfo.uid},
                <if test='userinfo.nickname !=null'> nickname=#{userinfo.nickname}, </if>
                <if test='userinfo.sex !=null'> sex=#{userinfo.sex}, </if>
                <if test='userinfo.birth !=null'> birth=#{userinfo.birth}, </if>
                <if test='userinfo.email !=null'> email=#{userinfo.email}, </if>
                <if test='userinfo.qq !=null'> qq=#{userinfo.qq}, </if>
            </set>
            where id=#{id}
        </script>
            """)
    public boolean updateUserInfo(int id, UserInfoEntity userInfo);
    

    @Select("select * from userinfo where uid=#{uid}")
    public UserInfoEntity findUserInfoByUserId(int uid);

    // @Select("select ui.id, ui.uid, ui.nickname, ui.sex, ui.birch, ui.email, ui.qq, ui.wechat, ui.updateTime from userinfo ui inner join user u on ui.uid==u.id where u.username=#{username}")
    // public UserInfo findUserInfoByUsername(String username);
}
