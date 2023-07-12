package com.xowen.controller;

import com.xowen.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class index {
    @RequestMapping("/")
    public String index(){
        return  "hello";
    }

    @RequestMapping("/test")
    public String test(HttpServletRequest request){
        String name = request.getParameter("name");
        String str_age = request.getParameter("age");
        int age = Integer.parseInt(str_age);
        System.out.println(name + " : " + age);
        return "ok";
    }

    @RequestMapping("/test2")
    public String test2(@RequestParam(name = "users", required = false) String username, String age){
        System.out.println(username + " : " + age);
        return "ok";
    }

    // 实体参数
    @RequestMapping("/test3")
    public String test3(User user){
        System.out.println(user);
        System.out.println(user.getUsername());
        System.out.println(user.getAddress());
        return user.toString();
    }

    // 数组参数
    @RequestMapping("/test4")
    public String test4(String[] hobby){
        System.out.println(Arrays.toString(hobby));
        return "ok";

    }

    @RequestMapping("/test5")
    public String test5(@RequestParam List<String> hobby){
        System.out.println(hobby);
        return "ok";

    }

    // 日期参数
    @RequestMapping("/test6")
    public String test6(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime){
        System.out.println(updateTime);
        return "ok";
    }

    // json参数
    @RequestMapping("/test7")
    public String test7(@RequestBody User user){
        System.out.println(user);
        System.out.println(user.getUsername());
        System.out.println(user.getAddress());
        return user.toString();
    }
    // 路径参数
    @RequestMapping("/test8/{id}/{book_class}")
    public String test7(@PathVariable Integer id, @PathVariable String book_class){
        System.out.println(id);
        System.out.println(book_class);
        return "ok";
        }
    }



