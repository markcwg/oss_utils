package com.markcwg.oss_component.exception;

import com.markcwg.oss_component.enums.ResultCode;

import java.util.Map;

/**
 * inputstream 流转换失败异常
 * @author markcwg
 * @date 2021/6/22 1:08 下午
 */
public class InputStreamFailedException extends BaseException {
    public InputStreamFailedException(Map<String, Object> data) {
        super(ResultCode.INPUT_STREAM_ERROR, data);
    }
}
