package com.demo.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nieyawei
 * @version 1.0
 * @className: ControllerExceptionHandler
 * @description:
 * @date 2019-06-12 21:26
 */

@ControllerAdvice
public class ControllerExceptionHandler {

    //这个注解是指当controller中抛出这个指定的异常类的时候，都会转到这个方法中来处理异常
    @ExceptionHandler(CommonException.class)
    //将返回的值转成json格式的数据
    @ResponseBody
    //返回的状态码
    public Map<String,Object> handlerUserNotExistException(CommonException exception){
        JSONObject result = new JSONObject();
        result.put("errCode", exception.getErrCode());
        result.put("errInfo", exception.getErrInfo());
        return result;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handlerUserNotExistException(Exception exception){
        JSONObject result = new JSONObject();
        result.put("errCode", "9");
        result.put("errInfo", exception.getMessage());
        return result;
    }
}

