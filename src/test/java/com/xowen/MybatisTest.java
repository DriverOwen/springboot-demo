package com.xowen;

import com.xowen.mapper.OperateLogMapper;
import com.xowen.mapper.UserMapper;
import com.xowen.pojo.EnglishUser;
import com.xowen.pojo.OperateLog;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
public class MybatisTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Test
    public void getUserMapper() {
        List<EnglishUser> userList = userMapper.list();
        userList.stream().forEach(user -> {
            System.out.println(user);
        });
    }

    @Test
    public void deleteUserMapper(){
        userMapper.deleteUser(42);
    }

    @Test
    public void insertUserMapper(){
        EnglishUser englishUser = new EnglishUser();
        short authority = 1;
        englishUser.setUsername("test39");
        englishUser.setPassword("123456");
        englishUser.setAuthrioty(authority);
        englishUser.setPhone("19130486635");
        englishUser.setEmail("1941352853@qq.com");
        englishUser.setRole("管理员");
        englishUser.setCreateTime(LocalDateTime.now());
        englishUser.setId(999);
        userMapper.insertUser(englishUser);
        System.out.println(englishUser.getId());
    }

    @Test
    public void updateUserMapper(){
        EnglishUser englishUser = new EnglishUser();
        short authority = 1;
        englishUser.setUsername("test39");
        englishUser.setPhone("110");
        englishUser.setId(62);
        userMapper.updateUser(englishUser);
    }

    @Test
    public void getUserById(){
        List<EnglishUser> englishUser = userMapper.getUserById(LocalDateTime.of(2000,1,1,0,0,0));
        System.out.println(englishUser);
    }

    @Test
    public void getUserByXml(){
//        List<EnglishUser> userList = userMapper.selectUserByXml("管理",(short) 2, LocalDateTime.of(1988,12,1,0,0,0),LocalDateTime.of(2000,12,1,0,0,0));
        List<EnglishUser> userList = userMapper.selectUserByXml("管理",(short) 2, null,null);
        userList.stream().forEach(user -> {
            System.out.println(user);
        });
    }

    @Test
    public void deleteUserByIds(){
        userMapper.deleteUserByIdsWithXml(Arrays.asList(50,51));
    }

    // 测试JWT生成
    @Test
    public void testGenJwt(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","xowen");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "xowen") // 签名算法
                .setClaims(claims) // 携带内容
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置有效期为1h
                .compact();
        System.out.println(jwt);
    }



    // 解析 JWT 令牌
    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("xowen")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoieG93ZW4iLCJpZCI6MSwiZXhwIjoxNjg5MDcyMzE2fQ.bBxGbnAk8H7i1APsvsZkxaUWb5BgSvjNvAfyT2srHF4")
                .getBody();
        System.out.println(claims);
    }

    @Test // 获取log日志
    public void testGetOperateLog(){
        List<OperateLog> operateLogList = operateLogMapper.getOperateLog();
        operateLogList.stream().forEach(operateLog -> {
            System.out.println(operateLog);
        });
    }
}
