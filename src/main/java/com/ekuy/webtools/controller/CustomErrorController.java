package com.ekuy.webtools.controller;

import com.ekuy.webtools.model.Result;
import com.ekuy.webtools.model.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("")
@Slf4j
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public Result handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        return new Result(ResultCodeEnum.REQUEST_ERROR, status.toString());
    }
}
