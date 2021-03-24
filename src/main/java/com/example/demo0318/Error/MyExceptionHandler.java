package com.example.demo0318.Error;


import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = BindException .class)
    @ResponseBody
    public String exceptionHandler(Exception e){
        System.out.println("发生了一个验证异常");
        return "全局异常监控，这是一种策略";
    }
}
