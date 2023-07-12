package com.xowen.mapper;

import com.xowen.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    @Select("select * from operate_log")
    public List<OperateLog> getOperateLog();

    @Insert("insert into operate_log(operate_user, operate_time, class_name, method_name, method_params, return_value, cost_time) values(" +
            "#{operateUser},#{operateTime},#{className},#{methodName},#{methodParams},#{returnValue},#{costTime})")
    public void insertOperateLog(OperateLog operateLog);

}
