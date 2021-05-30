package com.space.cornerstone.framework.core.annotation;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 模块名称注解
 *
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogModule {

    String name() default "";

    @AliasFor("name")
    String value() default "";
}
