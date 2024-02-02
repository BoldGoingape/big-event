package com.wenjuju.top.controller;

import com.wenjuju.top.bean.Result;
import com.wenjuju.top.bean.User;
import com.wenjuju.top.service.UserService;
import com.wenjuju.top.utils.jwtUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
//    创建用户
    @PostMapping("/register")
    public Result register(@Pattern(regexp ="^\\S{5,16}$" )String username, @Pattern(regexp ="^\\S{5,16}$" )String password){
            //查询用户
            User u= userService.findByUserName(username);
            if (u==null){
                //注册
                userService.register(username,password);
                return Result.success();
            }else {
                //占用
                return Result.error("用户名被占用");
            }
    }
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$")String username,@Pattern(regexp = "^\\S{5,16}$")String password){
        User loginUser = userService.findByUserName(username);
        if (loginUser==null){
            return Result.error("用户名错误");
        }
        if (password.equals(loginUser.getPassword())){
            Map<String,Object>claims=new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = jwtUtil.createToken(claims);

            return Result.success(token);
        }
        return Result.error("密码错误");
    }
}
