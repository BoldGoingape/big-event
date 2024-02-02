package com.wenjuju.top;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SpringBootTest
class BigEventApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    public void testGen(){
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");
       String token= JWT.create().withClaim("user",claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))//添加过期时间
                .sign(Algorithm.HMAC256("U0tZTUFQX2FiY18xMfg"));//指定算法，生成密钥
        System.out.println(token);
    }

    @Test
    public void testParse(){
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MDY3NTQxNDAsInVzZXIiOnsiaWQiOjEsInVzZXJuYW1lIjoi5byg5LiJIn19.8nGC1FdhzKmM77FDb6_XxtVM3vIneU1c35F3oo3Rm10";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("U0tZTUFQX2FiY18xMfg")).build();
        DecodedJWT verify = jwtVerifier.verify(token);//验证token 生成一个简析后的jwt
        Map<String,Claim>claims = verify.getClaims();
        System.out.println(claims.get("user"));
    }
}
