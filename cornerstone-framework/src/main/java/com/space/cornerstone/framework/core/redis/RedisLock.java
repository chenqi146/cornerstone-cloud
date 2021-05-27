package com.space.cornerstone.framework.core.redis;


import org.springframework.context.ApplicationEvent;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 *  分布式锁注解
 * -- ex: @RedisLock example --
 *<PRE>
 *<code>
 * {@literal @}RedisLock(key = "#param1+':'+#param2", waitTime = 100)
 *    public void loadDictToRedis(String param1, String param2) {
 *
 *    }
 *</code>
 *</PRE>
 *
 * @author chenqi
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisLock {

    /**
     *  锁的key 后缀
     * @see RedisLock#keyPrefix()
     * <p>key的生成方式支持spel表达式</p>
     *  <code>
     *      redisKey = keyPrefix + ":" + key;
     *  </code>
     * @return key
     */
    String key();

    /**
     *  锁的前缀
     * {@value} 默认LOCK
     * @see RedisLock#key()
     * <p>keyPrefix的生成方式支持spel表达式</p>
     * @return keyPrefix
     */
    String keyPrefix() default "LOCK";

    /**
        过期时间  默认5秒
       @see RedisLock#timeUnit() 单位
      */
    int expire() default 5;

    /**
     * 获取锁的等待时间  默认1秒
     * 单位为毫秒
     * @return
     */
    long waitTime() default 1000;

    /**
     *  锁的过期时间 单位  默认秒
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     *  是否抛出异常  默认true
     * @return
     */
    boolean isThrow() default true;

    /**
     * 业务提示内容 没抢到锁抛异常时
     * @return
     */
    String exceptionMsg() default "系统繁忙, 请稍后再试!";

    /**
     * Spring Expression Language (SpEL) attribute used for making the
     * event handling conditional.
     * <p>Default is {@code ""}, meaning the event is always handled.
     * <p>The SpEL expression evaluates against a dedicated context that
     * provides the following meta-data:
     * <ul>
     * <li>{@code #root.event}, {@code #root.args} for
     * references to the {@link ApplicationEvent} and method arguments
     * respectively.</li>
     * <li>Method arguments can be accessed by index. For instance the
     * first argument can be accessed via {@code #root.args[0]}, {@code #p0}
     * or {@code #a0}. Arguments can also be accessed by name if that
     * information is available.</li>
     * </ul>
     */
    String condition() default "";

}
