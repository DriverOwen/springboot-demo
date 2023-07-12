package com.xowen;

import com.xowen.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class BeanIoc {
    @Autowired
    private ApplicationContext applicationContext; // IOC容器对象

    @Test
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

}
