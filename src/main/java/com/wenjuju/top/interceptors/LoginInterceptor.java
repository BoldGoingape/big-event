package com.wenjuju.top.interceptors;

import com.wenjuju.top.bean.Result;
import com.wenjuju.top.utils.ThreadLocalUtil;
import com.wenjuju.top.utils.jwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return HandlerInterceptor.super.preHandle(request, response, handler);
//        拦击认证token
        String token = request.getHeader("Authorization");
        try{
            //redis 中获取同名的token
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(token);
            if (redisToken==null){
                throw new RuntimeException();
            }
            Map<String ,Object>claims= jwtUtil.parseToken(token);
            ThreadLocalUtil.set(claims);
            return true;//放行
        }catch (Exception e){
            response.setStatus(401);
            response.setHeader("location","token");
            return false;
        }
    }
//    清空ThreadLocal数据

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
