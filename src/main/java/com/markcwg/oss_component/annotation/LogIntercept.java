package com.markcwg.oss_component.annotation;

import java.lang.annotation.*;

/**
 * @author markcwg
 * @date 2021/6/21 7:57 下午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogIntercept {
    String message();
}
