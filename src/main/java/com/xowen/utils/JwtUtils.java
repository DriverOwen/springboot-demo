package com.xowen.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "jwt.conf")
public class JwtUtils {
    private String signKey;
    private Long expire;

    // 生成JWT令牌
    public String generateJwt(Map<String, Object> claims){
        System.out.println(signKey);
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, signKey) // 签名算法
                .setClaims(claims) // 携带内容
                .setExpiration(new Date(System.currentTimeMillis() + expire)) // 设置有效期为1h
                .compact();
        return jwt;
    }

    // 解析 JWT 令牌
    public Claims parseJwt(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
