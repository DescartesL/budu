package com.eula.budu.annotation;

import java.lang.annotation.*;

/**
 * 标注该注解的方法需要记录操作日志
 * @author DesLUO
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLogger {
    /**
     * 业务名称
     */
    String value() default "";

    /**
     * 是否当前日志记录到数据库中
     */
    boolean save() default true;
}
