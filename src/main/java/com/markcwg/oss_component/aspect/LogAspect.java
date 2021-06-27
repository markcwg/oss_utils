package com.markcwg.oss_component.aspect;

import com.markcwg.oss_component.annotation.LogIntercept;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static com.markcwg.oss_component.utils.LogUtils.logToFile;

/**
 * @author markcwg
 * @date 2021/6/21 7:41 下午
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    /**
     * 定义带有LogIntercept注解的方法切点
     */
    @Pointcut("@annotation(com.markcwg.oss_component.annotation.LogIntercept)")
    public void annotationPointCut() {}

    @Before("annotationPointCut()")
    public void testAnnotation(JoinPoint point) {
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        LogIntercept logIntercept = method.getAnnotation(LogIntercept.class);
        System.out.println(logIntercept.message());
    }

    /**
     * 异常日志切面
     * @param joinPoint 切入点
     * @param ex 异常信息
     */
    @AfterThrowing(throwing = "ex", pointcut = "execution(* com.markcwg.oss_component.*.*.*(..)))")
    public void logPoint(JoinPoint joinPoint, Throwable ex) {
        logToFile(joinPoint,ex);
    }
}
