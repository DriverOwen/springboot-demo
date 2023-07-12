package com.xowen.aop;

import com.alibaba.fastjson.JSONObject;
import com.xowen.mapper.OperateLogMapper;
import com.xowen.pojo.OperateLog;
import com.xowen.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class logAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired // 我们可以这么理解，httpserverletRequest是在前端控制器的，而前端控制器是在ioc容器里的，所以我们可以直接注入
    // 这里不明白为什么可以直接注入的,可以不用管先,或者直接跳到P188,看完就知道为什么可以了
    // 可以理解为spring自动将其交给IOC容器了
    private HttpServletRequest request;

    @Autowired
    private JwtUtils jwtUtils;

    @Around("@annotation(com.xowen.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        // 获取操作人ID jwt解析
        String jwt = request.getHeader("token");
        Claims claims = jwtUtils.parseJwt(jwt);
        Integer operateUser = (Integer) claims.get("id");

        // 获取操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        // 操作类名
        String className = joinPoint.getTarget().getClass().getName();

        // 操作方法名
        String  methodName = joinPoint.getSignature().getName();

        // 操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        Long begin =  System.currentTimeMillis();
        // 调用原始目标方法执行
        Object result = joinPoint.proceed();
        Long end = System.currentTimeMillis();



        // 方法返回值
        String returnValue = JSONObject.toJSONString(result);

        // 操作耗时
        long costTime = end - begin;

        // 记录日志
        OperateLog operateLog = new OperateLog(null, operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insertOperateLog(operateLog);


        return result;
    }
}
