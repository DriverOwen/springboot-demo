package com.xowen;

import com.example.EnableHeaderConfig;
import com.example.HeaderConfig;
import com.example.HeaderParser;
import com.example.TokenParser;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

//@Import({TokenParser.class}) // 导入普通类，交给ioc管理
//@Import({HeaderGenerator.class})
//@Import({HeaderConfig.class})
//@Import({MyImportSelector.class}) // 导入importSelector接口实现类，批量交给IOC管理
//@ComponentScan({"com.example","com.xowen"})
// No auto configuration classes found in META-INF/spring.factories nor in META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports. If you are using a custom packaging, make sure that file is correct.
@EnableHeaderConfig
@ServletComponentScan  // 在spring项目中启用servlet组件
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
