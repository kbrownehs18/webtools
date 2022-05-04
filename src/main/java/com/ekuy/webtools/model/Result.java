package com.ekuy.webtools.model;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private int code;
    private T data;
    private String msg;

    public Result(ResultCode resultCode) {
        code = resultCode.getCode();
        msg = resultCode.getMsg();
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(ResultCode resultCode, Object ...params) {
        code = resultCode.getCode();
        msg = String.format(resultCode.getMsg(), params);
    }

    public Result(ResultCode resultCode, T data, Object ...params) {
        code = resultCode.getCode();
        msg = String.format(resultCode.getMsg(), params);
        this.data = data;
    }

    public Result(ResultCode resultCode, T data) {
        this(resultCode, data, null);
    }

    public static Result failure(ResultCode... resultCodes) {
        return new Result(resultCodes.length>0 ? resultCodes[0] : ResultCodeEnum.UNKNOWN_EXCEPTION);
    }

    public static Result failure(ResultCode resultCode, Object... message) {
        return new Result(resultCode, message);
    }

    public static Result failure(int code, String message) {
        return new Result(code, message);
    }

    public static Result success(String key, Object data) {
        JSONObject json = new JSONObject();
        json.put(key, data);
        return Result.success(json);
    }

    public static Result success(Object data) {
        return new Result(ResultCodeEnum.OK.getCode(), data, ResultCodeEnum.OK.getMsg());
    }

    public static Result success(Object ...params) {
        if (params.length > 0 && params.length % 2 == 0) {
            JSONObject json = new JSONObject();
            for (int i=0; i<params.length; i++) {
                json.put((String)params[i], params[i+1]);
                i++;
            }

            return success(json);
        }

        return new Result(ResultCodeEnum.OK);
    }
}
