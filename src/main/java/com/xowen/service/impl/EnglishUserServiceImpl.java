package com.xowen.service.impl;


import com.xowen.mapper.UserMapper;
import com.xowen.pojo.EnglishUser;
import com.xowen.service.EnglishUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnglishUserServiceImpl implements EnglishUserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public EnglishUser login(EnglishUser englishUser) {
        return userMapper.login(englishUser);
    }
}
