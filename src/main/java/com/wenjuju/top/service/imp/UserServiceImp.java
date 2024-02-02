package com.wenjuju.top.service.imp;

import com.wenjuju.top.bean.User;
import com.wenjuju.top.mapper.UserMapper;
import com.wenjuju.top.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
@Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
      User u=  userMapper.findByuserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
    //加密
        //添加
        userMapper.add(username,password);
    }
}
