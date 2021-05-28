package com.space.cornerstone.framework.core.redis;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.space.cornerstone.framework.core.exception.BusinessException;
import com.space.cornerstone.framework.core.util.RedisLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * redis分布式锁 切面处理类
 *
 * @author chen qi
 * @date 2020-10-29 18:25
 **/
@Slf4j
@Aspect
@Component
public class RedisLockAspect {

    @Autowired
    private RedisClient redisClient;


    @Around("@annotation(RedisLock)")
    public Object aroundCache(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        final RedisLock annotation = methodSignature.getMethod().getAnnotation(RedisLock.class);

        final String keyPrefix = getRealForSpELKey(joinPoint, annotation.keyPrefix());
        final String key = getRealForSpELKey(joinPoint, annotation.key());
        String lockKey = keyPrefix + StrUtil.COLON + key;
        // 判断条件  默认执行
        final String condition = annotation.condition();
        if (StrUtil.isNotEmpty(condition)) {
            final String conditionResult = getRealForSpELKey(joinPoint, condition);
            if (StrUtil.equals(conditionResult, Boolean.FALSE.toString())) {
                return joinPoint.proceed();
            }
        }
        String value = UUID.randomUUID().toString();

        // 获取不到锁 跳过此执行方法
        int expire = annotation.expire();
        final TimeUnit timeUnit = annotation.timeUnit();
        if (timeUnit != TimeUnit.SECONDS) {
            expire = (int) timeUnit.toSeconds(expire);
        }

        final String format = StrUtil.format("线程id: {}, 类: {}, 方法: {}", Thread.currentThread().getId(),
                joinPoint.getTarget().getClass().getSimpleName(), methodSignature.getMethod().getName());
        // 尝试获取分布式锁  根据等待时间去重试获取锁
        try {
            if (!RedisLockUtil.lockWithWaitTime(redisClient, lockKey, value, expire, annotation.waitTime())) {
                if (!annotation.isThrow()) {
                    return null;
                }
                throw new BusinessException(annotation.exceptionMsg());
            }
        } catch (BusinessException businessException) {
            throw businessException;
        } catch (Exception e) {
            log.error("{}, 尝试获取分布式锁异常", format, e);
            RedisLockUtil.releaseDistributedLock(redisClient, lockKey, value);
            if (!annotation.isThrow()) {
                return null;
            }
            throw new BusinessException(annotation.exceptionMsg());
        }

        try {

            log.debug("{} ,获取到分布式锁: key: {}, value: {}", format, lockKey, value);
            LockExpandDaemonRunnable runnable = new LockExpandDaemonRunnable(redisClient, lockKey, value, expire);
            // 开启redis 过期时间 续期守护线程
            Thread thread = new Thread(runnable);
            thread.setDaemon(Boolean.TRUE);
            thread.start();

            Object proceed = null;
            try {
                proceed = joinPoint.proceed();
            } catch (Exception throwable) {
                log.error("{}, 分布式锁方法执行异常", format, throwable);
                if (annotation.isThrow()) {
                    throw throwable;
                }
            } finally {
                runnable.stop();
                thread.interrupt();
            }
            return proceed;
        } finally {
            if (!RedisLockUtil.releaseDistributedLock(redisClient, lockKey, value)) {
                log.error("{}, 分布式锁释放失败, key: {}, value: {}", format, lockKey, value);
            } else {
                log.debug("{}, 分布式锁释放成功, key: {}, value: {}", format, lockKey, value);
            }

        }

    }


    /**
     * 用于SpEL表达式解析.
     */
    private final SpelExpressionParser parser = new SpelExpressionParser();
    /**
     * 用于获取方法参数定义名字.
     */
    private final DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

    private static final String SPEL_FLAG = "#";

    private String getRealForSpELKey(ProceedingJoinPoint joinPoint, String key) {
        return key.contains(SPEL_FLAG) ? this.generateKeyBySpEL(key, joinPoint) : key;
    }

    /**
     * 获取spel表达式的值
     *
     * @param spELString spel表达式
     * @param joinPoint  切入点
     * @return
     */
    private String generateKeyBySpEL(String spELString, ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = nameDiscoverer.getParameterNames(methodSignature.getMethod());
        if (ArrayUtil.isEmpty(paramNames)) {
            return spELString;
        }
        Expression expression = parser.parseExpression(spELString);
        EvaluationContext context = new StandardEvaluationContext();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {

            context.setVariable(paramNames[i], args[i]);
        }
        final Object value = expression.getValue(context);
        if (value == null) {
            return spELString;
        }
        return value.toString();
    }

}
