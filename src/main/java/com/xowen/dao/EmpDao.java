package com.xowen.dao;

import com.xowen.pojo.Emp;

import java.util.List;

public interface EmpDao {
    // 获取员工列表
    public List<Emp> listEmp();
}
