package com.markcwg.oss_component.common;

import com.markcwg.oss_component.exception.BaseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常信息捕获
 * @author markcwg
 * @date 2021/6/22 1:13 下午
 */
@ControllerAdvice(basePackages = "com.markcwg.oss_component.controller")
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ErrorResult handleGlobalException(BaseException ex, HttpServletRequest request) {
        return new ErrorResult(ex, request.getRequestURI());
    }
}
