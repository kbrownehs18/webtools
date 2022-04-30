package com.ekuy.webtools.aspect;

import com.ekuy.webtools.exception.BusinessException;
import com.ekuy.webtools.model.Result;
import com.ekuy.webtools.model.ResultCodeEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;


@RestControllerAdvice
public class ExceptionAspect {

    @ExceptionHandler(value = {BindException.class})
    public Result exceptionHandler(BindException e) {
        e.printStackTrace();
        return Result.failure(ResultCodeEnum.INVALID_BIND_PARAMS,
                e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public Result exceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.failure(ResultCodeEnum.UNKNOWN_EXCEPTION);
    }

    @ExceptionHandler(value = {BusinessException.class})
    public Result exceptionHandler(BusinessException e) {
        e.printStackTrace();
        return Result.failure(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = {ServletException.class})
    public Result exceptionHandler(ServletException e) {
        e.printStackTrace();
        return Result.failure(ResultCodeEnum.INVALID_REQUEST_PARAMS, e.getMessage());
    }
}
