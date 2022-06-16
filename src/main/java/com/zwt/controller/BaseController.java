package com.zwt.controller;

import com.zwt.utils.error.BusinessException;
import com.zwt.utils.error.EmBusinessError;
import com.zwt.utils.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class BaseController {
    //拦截抛出的异常并且处理
    @ExceptionHandler(Exception.class)
    /*
        ExceptionHandler：1.一次声明、全接口有效；2.就近原则
        原理：
        1.程序一开始会把所有的异常处理方法加载到Map中去，key为异常类型，value为解决方法
        2.处理异常A时，会去Map取出异常A对应的method
        3.如果没有，则在Map中把所有key取出来，找出所有异常A的父类
        4.再找出继承深度最小的父类，拿到它的method
        5.最后把异常A和method存到Map中
    */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex) {
        Map<String, Object> responseData = new HashMap<>();
        if (ex instanceof BusinessException) {
            BusinessException exception = (BusinessException) ex;
            responseData.put("errCode", exception.getErrCode());
            responseData.put("errMsg", exception.getErrMsg());
        } else if (ex instanceof SQLException) {
            responseData.put("errCode", EmBusinessError.SQL_ERROR.getErrCode());
            responseData.put("errMsg", EmBusinessError.SQL_ERROR.getErrMsg());
        }else {
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }

        return CommonReturnType.create(responseData, "fail");
    }
}
