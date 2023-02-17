package com.eula.budu.annotation;

import java.lang.annotation.*;

/**
 * @author DesLUO
 * @description: 自定义操作日志注解
 */
@Target(ElementType.METHOD) // 注解防止的目标位置，也就是方法级别
@Retention(RetentionPolicy.RUNTIME) // 注解在那个阶段执行
@Documented
public @interface BusinessLogger {
    String value() default ""; // 操作模块
    String type() default ""; // 操作类型
    String desc() default ""; // 操作说明
    boolean save() default true; // 是否将当前日志记录到数据库中
}
