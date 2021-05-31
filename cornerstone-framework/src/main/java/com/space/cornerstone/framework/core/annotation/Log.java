package com.space.cornerstone.framework.core.annotation;

import com.space.cornerstone.framework.core.enums.OperationLogType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * 记录：日志名称，日志类型，日志备注
 *
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 日志名称
     *
     * @return
     */
    String name() default "";

    /**
     * 日志名称
     *
     * @return
     */
    @AliasFor("name")
    String value() default "";

    /**
     * 日志类型
     *
     * @return
     */
    OperationLogType type() default OperationLogType.OTHER;

    /**
     * 日志备注
     *
     * @return
     */
    String remark() default "";

}
