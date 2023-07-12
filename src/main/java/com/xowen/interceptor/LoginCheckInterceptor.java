package com.xowen.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.xowen.pojo.Result;
import com.xowen.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;
    @Override  // 目标资源方法运行前，返回true: 放行，返回false：不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI().toString();
        log.info("请求的url：{}", url);
        if(url.contains("login")){
            log.info("登录操作，放行...");
            return true;
        }
        // 逻辑处理，比如登录校验
        String jwt = request.getHeader("token");
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            // 手动 对象 转换为json，因为之前result在controller注解下面，会自动转换，但是在过滤器下面不会
            String notLogin = JSONObject.toJSONString(error);
            // writer 输出流
            response.getWriter().write(notLogin);
            return false;
        }

        // jwt 不为空，开始解析令牌
        try {
            jwtUtils.parseJwt(jwt);
        } catch (Exception e){
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            // 手动 对象 转换为json，因为之前result在controller注解下面，会自动转换，但是在过滤器下面不会
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        log.info("令牌合法，放行");
        return true;
    }

    @Override // 目标资源执行完了后 执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        log.info("返回了拦截器");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        log.info("结束了拦截器");
    }
}
