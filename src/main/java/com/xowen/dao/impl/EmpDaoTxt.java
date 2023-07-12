package com.xowen.dao.impl;

import com.xowen.dao.EmpDao;
import com.xowen.pojo.Emp;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "owen") // 将当前类交给ICO容器管理，成为IOC容器中的bean ,value取别名
public class EmpDaoTxt implements EmpDao {
    @Override
    public List<Emp> listEmp() {
        String file = this.getClass().getClassLoader().getResource("emp.txt").getFile();
        System.out.println(file);
        List<Emp> empList = new ArrayList<>();
        // 代替从txt获取数据，也可以抽象为从数据库获取数据
        Emp emp1 = new Emp();
        emp1.setName("owen");
        emp1.setAddr("dazhou");
        emp1.setAge(11);
        emp1.setGender(1);

        Emp emp2 = new Emp();
        emp2.setName("xiaoju");
        emp2.setAddr("chengdu");
        emp2.setAge(12);
        emp2.setGender(0);

        Emp emp3 = new Emp();
        emp3.setName("tom");
        emp3.setAddr("合肥");
        emp3.setAge(22);
        emp3.setGender(0);

        empList.add(emp1);
        empList.add(emp2);
        empList.add(emp3);

        return empList;
    }
}
