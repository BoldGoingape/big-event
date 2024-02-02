package com.wenjuju.top.mapper;

import com.wenjuju.top.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper {
//    查询用户
    @Select("select * from user where username=${username}")
    User findByuserName(@Param("username") String username);
//    添加用户
    @Insert("insert into user (username,password,create_time,update_time)"+"values(#{username},#{password},now(),now())")
    void add(String username, String password);
}
