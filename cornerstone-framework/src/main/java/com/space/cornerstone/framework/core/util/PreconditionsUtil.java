package com.space.cornerstone.framework.core.util;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import com.space.cornerstone.framework.core.exception.BusinessException;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName PreconditionsUtil.java
 * @Description PreconditionsUtil
 * @createTime 2021年05月23日 18:59:00
 */
public final class PreconditionsUtil {


    public static <T> T checkNotNull(T t) {
        try {
            return Preconditions.checkNotNull(t);
        } catch (NullPointerException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * @Description 为空抛出异常
     * @author chen qi
     * @param t
     * @param errorMessageTemplate 错误信息模板
     * @param errorMessageArgs 错误行参数
     * @since 2021-05-23 19:06
     * @return : T
     * @throws  BusinessException
     */
    public static <T> T checkNotNull(T t, @Nullable String errorMessageTemplate, @Nullable Object... errorMessageArgs) {
        try {
            return Preconditions.checkNotNull(t, errorMessageTemplate, errorMessageArgs);
        } catch (NullPointerException e) {
            throw new BusinessException(StrUtil.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    /***
     * 校验表达式是否正确
     * @Description
     * @author chen qi
     * @param expression 为false则抛出异常
     * @since 2021-05-23 19:03
     * @throws BusinessException
     */
    public static void checkArgument(boolean expression) {
        try {
            Preconditions.checkArgument(expression);
        } catch (IllegalArgumentException e) {
            throw new BusinessException(e.getMessage());
        }
    }


    /**
     * @Description  校验表达式是否正确
     * @author chen qi
     * @param expression 为false则抛出异常
     * @param errorMessageTemplate 错误信息模板
     * @param errorMessageArgs 错误行参数
     * @since 2021-05-23 19:03
     * @throws  BusinessException
     */
    public static void checkArgument(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object... errorMessageArgs) {
        try {
            Preconditions.checkArgument(expression);
        } catch (IllegalArgumentException e) {
            throw new BusinessException(StrUtil.format(errorMessageTemplate, errorMessageArgs));
        }
    }
}
