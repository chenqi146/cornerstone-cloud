package com.space.cornerstone.framework.core.constant;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName Constant.java
 * @Description 基础常量
 * @createTime 2021年05月20日 23:42:00
 */
public final class Constant {

    /**
     * 登录请求 用户名字段
     */
    public static final String LOGIN_USERNAME_PARAM = "s";

    public static final Long PAGE_SIZE = 10L;
    public static final Long PAGE_NUM = 1L;


    public static final String CREATE_TIME = "createTime";
    public static final String UPDATE_TIME = "updateTime";
    public static final String UPDATE_BY = "updateBy";
    public static final String CREATE_BY = "createBy";

    /**
     * traceId 跟踪请求
     */
    public static final String TRACE_ID = "traceId";

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 用户类型  管理员
     */
    public static final String USER_TYPE_ADMIN = "ADMIN";

    public static final Long ROOT_ID = 0L;

    public static final Integer LOGIN_TYPE = 1;
    public static final Integer LOGOUT_TYPE = 2;

    public static final String ASYNC_EXECUTOR = "asyncTaskExecutor";

    /**
     * 字典缓存前缀
     */
    public static final String DICT = "DICT:";

}
