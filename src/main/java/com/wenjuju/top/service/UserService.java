package com.wenjuju.top.service;

import com.wenjuju.top.bean.User;

public interface UserService {
    //根据用户名查询用户
    User findByUserName(String username);

    //注册
    void register(String username, String password);

    void update(User user);
    //跟新用户头像
    void updateAvater(String avaterUrl);
// 更新密码
    void updatePwd(String newPwd);
}
