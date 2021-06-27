package com.markcwg.oss_component.exception;

import com.markcwg.oss_component.enums.ResultCode;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常类
 * @author markcwg
 * @date 2021/6/22 12:57 下午
 */
@Getter
public class BaseException extends RuntimeException{
    private ResultCode error;
    private Map<String, Object> data = new HashMap<>();

    public BaseException(ResultCode error, Map<String, Object> data) {
        super(error.getMessage());
        this.error = error;
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    protected BaseException(ResultCode error, Map<String, Object> data, Throwable cause) {
        super(error.getMessage(), cause);
        this.error = error;
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }
}
