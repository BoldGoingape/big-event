package com.wenjuju.top.mapper;

import com.wenjuju.top.bean.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
//    查询用户
    @Select("select * from user where username=${username}")
    User findByuserName(@Param("username") String username);
//    添加用户
    @Insert("insert into user (username,password,create_time,update_time)"+"values(#{username},#{password},now(),now())")
    void add(String username, String password);

    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);
    @Update("update user set user_pic=#{avaterUrl},update_time=now() where id=#{id}")
    void updateAvater(String avaterUrl,Integer id);
    @Update("update user set password=#{newPwd},update_time=now() where id=#{id}")
    void updatePwd(String newPwd,Integer id);
}
