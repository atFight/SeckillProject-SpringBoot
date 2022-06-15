package com.zwt.controller;

import com.zwt.utils.error.BusinessException;
import com.zwt.utils.error.EmBusinessError;
import com.zwt.utils.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

    //拦截抛出的异常并且处理
    @ExceptionHandler(BusinessException.class)
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
