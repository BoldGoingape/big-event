package com.wenjuju.top.service.imp;

import com.wenjuju.top.bean.User;
import com.wenjuju.top.mapper.UserMapper;
import com.wenjuju.top.service.UserService;
import com.wenjuju.top.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

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

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }
    //跟新用户头像
    @Override
    public void updateAvater(String avaterUrl) {
        Map<String,Object>map = ThreadLocalUtil.get();
        Integer id=(Integer)map.get("id");
        userMapper.updateAvater(avaterUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object>map = ThreadLocalUtil.get();
        Integer id=(Integer)map.get("id");
        userMapper.updatePwd(newPwd,id);
    }
}
