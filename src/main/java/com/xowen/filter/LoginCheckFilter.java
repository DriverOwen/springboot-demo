package com.xowen.filter;

import com.alibaba.fastjson.JSONObject;
import com.xowen.pojo.Result;
import com.xowen.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Slf4j
//@WebFilter(urlPatterns = "/*") // 拦截所有请求路径
public class LoginCheckFilter implements Filter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 因为tomcat传入的request实际上是HTTPrequest  这里是多态的思想
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI().toString();
        log.info("请求的url：{}", url);
        if(url.contains("login")){
            log.info("登录操作，放行...");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 逻辑处理，比如登录校验
        String jwt = req.getHeader("token");
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            // 手动 对象 转换为json，因为之前result在controller注解下面，会自动转换，但是在过滤器下面不会
            String notLogin = JSONObject.toJSONString(error);
            // writer 输出流
            res.getWriter().write(notLogin);
            return;
        }

        // jwt 不为空，开始解析令牌
        try {
            jwtUtils.parseJwt(jwt);
        } catch (Exception e){
            log.info("解析令牌失败，返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            // 手动 对象 转换为json，因为之前result在controller注解下面，会自动转换，但是在过滤器下面不会
            String notLogin = JSONObject.toJSONString(error);
            res.getWriter().write(notLogin);
            return;
        }

        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);

        // controller处理完，返回给浏览器前的逻辑处理 比如加上一些数据包信息
    }
}
