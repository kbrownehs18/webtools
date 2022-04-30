package com.ekuy.webtools.exception;

import com.ekuy.webtools.model.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    private int code;

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMsg());
        code = resultCode.getCode();
    }

    public BusinessException(ResultCode resultCode, Object ...params) {
        super(String.format(resultCode.getMsg(), params));
        code = resultCode.getCode();
    }
}
