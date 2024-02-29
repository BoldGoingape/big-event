package com.wenjuju.top.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.wenjuju.top.bean.Result;
import com.wenjuju.top.bean.User;
import com.wenjuju.top.service.UserService;
import com.wenjuju.top.utils.ThreadLocalUtil;
import com.wenjuju.top.utils.jwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@RestController
@Tag(name = "用户管理")
@ApiSupport(author = "割爱之举")
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
//    创建用户
@Operation(summary = "创建用户", description = "")
@Parameters({
        @Parameter(name = "username", description = "账号", required = true, in = ParameterIn.QUERY),
        @Parameter(name = "password", description = "密码", required = true, in = ParameterIn.QUERY)
})
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
    @Operation(summary = "用户登录", description = "账号密码登录")
    @Parameters({
            @Parameter(name = "username", description = "账号", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "password", description = "密码", required = true, in = ParameterIn.QUERY)
    })
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
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,1, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }
    //获取用户信息
    @Operation(summary = "获取用户信息", description = "账号密码登录")
    @GetMapping("/userInfo")
//    public Result <User>getUserinfo(@RequestHeader(name = "Authorization") String token){
    public Result <User>getUserinfo(/*@RequestHeader(name = "Authorization") String token*/){
//        Map<String, Object> map = jwtUtil.parseToken(token);
//        String username  =(String) map.get("username");
        Map <String,Object>map = ThreadLocalUtil.get();
        String username=(String) map.get("username");
        User userInfo = userService.findByUserName(username);
        return Result.success(userInfo);
    }
    @Operation(summary = "更新用户信息")
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
    userService.update(user);
    return Result.success();
    }
//    跟新用户头像
@Operation(summary = "更新用户头像", description = "更新用户头像")
@Parameters({
        @Parameter(name = "avaterUrl", description = "头像URl", required = true, in = ParameterIn.QUERY),
})
    @PatchMapping("updateAvater")
    public Result updateAvater(@RequestParam @URL String avaterUrl){
        userService.updateAvater(avaterUrl);
        return Result.success();
    }
    //更新用户密码
    @Operation(summary = "修改密码", description = "修改用户密码")
//    @Parameters({
//            @Parameter(name = "oldPwd", description = "旧密码", required = true, in = ParameterIn.QUERY),
//            @Parameter(name = "newPwd", description = "新密码", required = true, in = ParameterIn.QUERY),
//            @Parameter(name = "rePwd", description = "重复密码", required = true, in = ParameterIn.QUERY)
//    })
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String>params,@RequestHeader("Authorization") String token){
        //校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要参数");
        }
        //校验原始密码 用户名查询 原始密码
        Map<String,Object>map=ThreadLocalUtil.get();
        String username =(String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!loginUser.getPassword().equals(oldPwd)){
            return Result.error("原密码填写不正确");
        }
        if (!newPwd.equals(rePwd)){
            return Result.error("两次填写的密码不一致");
        }
        userService.updatePwd(newPwd);
        //删除redis中的key
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }
}
