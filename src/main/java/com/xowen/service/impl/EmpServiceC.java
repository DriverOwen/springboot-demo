package com.xowen.service.impl;

import com.xowen.dao.EmpDao;
import com.xowen.dao.impl.EmpDaoTxt;
import com.xowen.mapper.UserMapper;
import com.xowen.pojo.Emp;
import com.xowen.pojo.EnglishUser;
import com.xowen.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service // 将当前类交给IOC容器管理，成为IOC容器中的bean
public class EmpServiceC implements EmpService {

    @Autowired //运行时，IOC容器会提供该类型的bean对象，并赋值给该变量，依赖注入。 这样就可以到达 empDao代码修改时不需要修改service的代码
    private UserMapper userMapper;
    @Autowired
    private EmpDao empDao;
    @Override
    public List<Emp> listEmp() {
        // 1. 调用dao，获取数据
        List<Emp> empList = empDao.listEmp();
        List<EnglishUser> test = userMapper.list();
        System.out.println(test);
        empList.forEach(emp -> {
            Integer gender = emp.getGender();
            if(gender == 1){
                emp.setGender(1+990);
            }else{
                emp.setGender(1+200);
            }
        });
        return empList;
    }
}
