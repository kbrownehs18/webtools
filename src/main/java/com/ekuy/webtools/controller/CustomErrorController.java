package com.ekuy.webtools.controller;

import com.ekuy.webtools.model.Result;
import com.ekuy.webtools.model.ResultCodeEnum;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
public class CustomErrorController implements ErrorController {
    @RequestMapping({"${server.error.path:${error.path:/error}}"})
    public Result handleError(HttpServletRequest request) {
        return new Result(ResultCodeEnum.NOT_FOUND);
    }
}
