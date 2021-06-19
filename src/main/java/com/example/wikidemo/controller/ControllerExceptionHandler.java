package com.example.wikidemo.controller;

import com.example.wikidemo.resp.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @ClassName ControllerExceptionHandler
 * @Description 统一异常处理、数据预处理等
 * @Author xiao
 * @Date 2021-06-19 18:04
 * @Version 1.0
 **/
@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /*
     * @Author xiao
     * @Description Validation异常统一处理
     * @Date 18:09 2021-06-19
     * @Param [e]
     * @return com.example.wikidemo.resp.CommonResp
     **/
    @ExceptionHandler(value= BindException.class)
    @ResponseBody
    public CommonResp validExceptionHandler(BindException e){
        CommonResp commonResp = new CommonResp();
        LOG.warn("参数校验失败:{}",e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return commonResp;
    }
}
