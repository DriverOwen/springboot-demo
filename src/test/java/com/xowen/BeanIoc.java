package com.xowen;

import com.example.HeaderParser;
import com.example.TokenParser;
import com.example.HeaderGenerator;

import com.xowen.starterDemo.JwtUtilsStarter;
import com.xowen.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class BeanIoc {
    @Autowired
    private ApplicationContext applicationContext; // IOC容器对象

    // 没有手动注册到IOC容器，spring 自动注册的
    @Autowired
    private JwtUtilsStarter jwtUtilsStarter;

    @Test // bean的主动获取方式
    public void testGetBean(){
        // 根据bean的名称获取 注意是小写
        JwtUtils jwtUtils1 = (JwtUtils) applicationContext.getBean("jwtUtils");
        System.out.println(jwtUtils1);
        // 根据bean的类型获取
        JwtUtils jwtUtils2 = (JwtUtils) applicationContext.getBean(JwtUtils.class);
        System.out.println(jwtUtils2);
        // 根据bean 的名称及类型获取
        JwtUtils jwtUtils3 = (JwtUtils) applicationContext.getBean("jwtUtils", JwtUtils.class);
        System.out.println(jwtUtils3);
    }


    @Test // 调用spring启动时自动配置的bean  private Gson gson @Autowire
    public void testAutoBean(){


    }

    // 测试第三方依赖
    @Test
    public void testTokenParser(){
        System.out.println(applicationContext.getBean(TokenParser.class));
    }
    @Test
    public void testHeaderParser(){
        System.out.println(applicationContext.getBean(HeaderParser.class));
    }
    @Test
    public void testHeaderGenParser(){
        System.out.println(applicationContext.getBean(HeaderGenerator.class));
    }

    // 测试 starterDemo 自动注入
    @Test
    public void testStarterDemo(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", "test");
        claims.put("username", "xowen");
        String jwt = jwtUtilsStarter.generateJwt(claims);
        System.out.println(jwt);
    }
}
