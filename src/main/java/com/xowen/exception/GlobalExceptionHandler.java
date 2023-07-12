package com.xowen.exception;

import com.xowen.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // 捕获所有异常，让程序出错的时候 正常运行
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("出现未知错误，请联系管理员");
    }
}
