package com.xowen.controller;

import com.xowen.anno.Log;
import com.xowen.pojo.Address;
import com.xowen.pojo.Emp;
import com.xowen.pojo.Result;
import com.xowen.service.EmpService;
import com.xowen.service.impl.EmpServiceTxt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class response {

    @Autowired
    private EmpService empService;


    @RequestMapping("/get_info")
    public Result get_info(){
        // 1. 调用service
        List<Emp> emplist =  empService.listEmp();
        System.out.println("hello");
        return Result.success(emplist);
    }

    @RequestMapping("/get_add")
    public Result get_add(){
        Address add = new Address();
        add.setProvince("sichuan");
        add.setCity("chengdu");
        return Result.success(add);
    }

    @Log
    @RequestMapping("/list_add")
    public Result list_add(){
        List<Address> myadd = new ArrayList<>();

        Address add1 = new Address();
        add1.setProvince("sichuan");
        add1.setCity("chengdu");

        Address add2 = new Address();
        add2.setProvince("anhui");
        add2.setCity("hefei");

        myadd.add(add1);
        myadd.add(add2);
        return Result.success(myadd);
    }
}
