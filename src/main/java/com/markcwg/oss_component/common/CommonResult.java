package com.markcwg.oss_component.common;

import com.markcwg.oss_component.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author markcwg
 * @date 2021/6/21 7:32 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private Boolean success;
    private T data;

    public static <T> CommonResult<T> ok (T data, ResultCode resultCode) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setSuccess(resultCode.getSuccess());
        result.setData(data);
        return result;
    }

    public static <T> CommonResult<T> error (T data, ResultCode resultCode) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setSuccess(resultCode.getSuccess());
        result.setData(data);
        return result;
    }
}

