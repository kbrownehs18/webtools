package com.ekuy.webtools.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum implements ResultCode {
    OK(0, "操作成功"),
    SUCCESS(200, "操作成功"),
    UNKNOWN_EXCEPTION(500, "未知异常，请联系系统管理员"),
    FORBID(403, "服务无权访问"),
    NOT_FOUND(404, "服务未找到"),
    FAILURE(5000, "%s"),
    INVALID_REQUEST_PARAMS(5001, "请求参数错误[%s]"),
    INVALID_BIND_PARAMS(5002, "绑定参数错误[%s]"),
    REQUEST_ERROR(5500, "请求错误[%s]");

    final int code;
    final String msg;
}
