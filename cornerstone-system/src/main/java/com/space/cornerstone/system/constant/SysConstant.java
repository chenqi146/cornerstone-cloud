package com.space.cornerstone.system.constant;

public class SysConstant {
    /**
     *  验证码redis前缀
     */
    public static final String CAPTCHA_CODE = "Captcha:";

    /**
     * 验证码过期时间
     */
    public static final Integer CAPTCHA_EXPIRATION = 5;

    /**
     * 用户锁定密码时间
     */
    public static final Integer USER_LOCK_TIME = 10;

    /**
     * 用户锁住密码 redis前缀
     */
    public static final String USER_LOCK_KEY = "USER_LOCK_KEY";

    /**
     * 用户密码错误次数限制  达到限制锁定
     */
    public static final Integer USER_LOCK_ERROR_LIMIT = 5;

    /**
     * 菜单层次 第一层
     */
    public static final Integer LEVEL_ROOT = 1;
}