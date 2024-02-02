package com.wenjuju.top.controller;

import com.wenjuju.top.bean.Result;
import com.wenjuju.top.utils.jwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
//    public Result <String> list(@RequestHeader(name = "Authorization") String token, HttpServletResponse response){
//       try {
//           Map<String ,Object>claims= jwtUtil.parseToken(token);
//           return Result.success("文章的信息");
//       }catch (Exception e){
//           response.setStatus(401);
//           return Result.error("token异常");
//       }
//    }
    public Result <String> list(String token, HttpServletResponse response){//使用拦截器
            return Result.success("文章的信息");
    }
}
