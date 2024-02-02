package com.wenjuju.top.interceptors;

import com.wenjuju.top.bean.Result;
import com.wenjuju.top.utils.jwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return HandlerInterceptor.super.preHandle(request, response, handler);
//        拦击认证token
        String token = request.getHeader("Authorization");
        try{
            Map<String ,Object>claims= jwtUtil.parseToken(token);
            return true;//放行
        }catch (Exception e){
            response.setStatus(401);
            return false;
        }
    }
}
