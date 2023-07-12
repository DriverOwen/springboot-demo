package com.xowen.controller;

import com.xowen.anno.Log;
import com.xowen.pojo.EnglishUser;
import com.xowen.pojo.Result;
import com.xowen.service.EnglishUserService;
import com.xowen.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class EnglishUserCtl {

    @Autowired
    private EnglishUserService englishUserService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")

    public Result login(EnglishUser englishUser) throws Exception {
        // 登录成功，生成令牌，下发令牌
        EnglishUser e = englishUserService.login(englishUser);
        if(e != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String jwt = jwtUtils.generateJwt(claims);

            return Result.success(jwt);
        }
        // 登录失败，返回错误信息

        return Result.error("用户或者密码错误");
    }

    @PostMapping("/isLogin")
    @Log
    public Result isLogin(EnglishUser englishUser){

        return Result.success("登录成功");
    }
}
