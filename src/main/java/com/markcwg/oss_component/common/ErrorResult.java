package com.markcwg.oss_component.common;

import com.markcwg.oss_component.exception.BaseException;
import lombok.Data;
import lombok.ToString;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常信息返回实体类
 * @author markcwg
 * @date 2021/6/22 12:51 下午
 */
@Data
@ToString
public class ErrorResult {
    private int code;
    private String message;
    private String path;
    private Instant timestamp;
    private Map<String, Object> data = new HashMap<String, Object>();

    public ErrorResult() {
    }

    public ErrorResult(BaseException ex, String path) {
        this(ex.getError().getCode(), ex.getError().getMessage(), path, ex.getData());
    }

    public ErrorResult(int code, String message, String path, Map<String, Object> data) {
        this.code = code;
        this.message = message;
        this.path = path;
        this.timestamp = Instant.now();
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }
}
