package com.awei.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ConfigurationProperties(prefix = "jwt")    //加载application.yml中jwt节点的信息
public class JWTGenerator {
   private String key;  //密钥，名称要与application.yml中一致
   private long expiration; //过期时间，名称要与application.yml中一致

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    //生成token
    public String getToken(String username, String password) {
        long curTime = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder()
                .setId(username).setSubject(password)   //使用用户名和用户密码
                .setIssuedAt(new Date(curTime))     //设置token创建时间
                .signWith(SignatureAlgorithm.ES256, key);   //进行签名，HS256方式
        if (expiration > 0){
            builder.setExpiration(new Date(curTime + expiration));  //设置token过期时间
        }
        return builder.compact();   //生成token
    }

    //解析token
    public Claims parser(String token){
        Claims c = Jwts.parser().setSigningKey(key) //设置签名密钥为yyh
                .parseClaimsJws(token).getBody();   //解析token
        String username = c.getId();    //获取token中的用户名
        String password = c.getSubject();   //获取token中的用户密码
        Date createDate = c.getIssuedAt();     //获取token的创建时间
        Date expirationDate = c.getExpiration();    //获取token的过期时间
        return c;
    }
}
