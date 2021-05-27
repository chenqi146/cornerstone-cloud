package com.space.cornerstone.framework.core.constant;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName Constant.java
 * @Description 基础常量
 * @createTime 2021年05月20日 23:42:00
 */
public final class Constant {

    public static final Long PAGE_SIZE = 10L;
    public static final Long PAGE_NUM = 1L;


    public static final String CREATE_TIME = "CREATE_TIME";
    public static final String UPDATE_TIME = "UPDATE_TIME";
    public static final String UPDATE_BY = "UPDATE_BY";
    public static final String CREATE_BY = "CREATE_BY";


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

    public static final String CAPTCHA_CODE = "Captcha:";
    public static final Integer CAPTCHA_EXPIRATION = 5;

    public static final Integer USER_LOCK_TIME = 10;
    public static final String USER_LOCK_KEY = "USER_LOCK_KEY";
    public static final Integer USER_LOCK_ERROR_LIMIT = 5;

}
