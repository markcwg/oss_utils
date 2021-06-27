package com.markcwg.oss_component.enums;

import lombok.Getter;

/**
 * 统一返回结果的枚举
 * @author markcwg
 * @date 2021/6/22 11:52 上午
 */
@Getter
public enum ResultCode {
    /** 操作成功 */
    SUCCESS(true,2000,"操作成功"),
    /** 获取签名成功 */
    MARK_SUCCESS(true, 2001, "获取签名成功"),
    /** 操作失败 */
    ERROR(false, 3000, "操作失败"),
    /** input stream 转换错误 */
    INPUT_STREAM_ERROR(false, 3001, "在将文件转换成 inputstream 时出错")
    ;
    /** 是否操作成功 */
    private final Boolean success;
    /** 状态码 */
    private final Integer code;
    /** 消息内容 */
    private final String message;

     ResultCode(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
