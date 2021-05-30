package com.space.cornerstone.framework.core.exception;

import com.space.cornerstone.framework.core.enums.BaseEnum;

/**
 * @EnumName: CommonEnum
 * @Description: CommonEnum
 * @Author: chen qi
 * @Date: 2019/12/22 19:23
 * @Version: 1.0
 **/

public enum CommonEnum implements BaseErrorInfoInterface, BaseEnum<Integer> {

    /**
     * 操作成功
     **/
    SUCCESS(200, "操作成功"),
    /**
     * 非法访问
     **/
    UNAUTHORIZED(401, "非法访问"),
    /**
     * 没有权限
     **/
    NOT_PERMISSION(403, "没有权限，请联系管理员授权"),
    /**
     * 你请求的资源不存在
     **/
    NOT_FOUND(404, "你请求的资源不存在"),
    /**
     * 操作失败
     **/
    FAIL(500, "操作失败"),
    /**
     * 登录失败
     **/
    LOGIN_EXCEPTION(4000, "登录失败"),
    /**
     * 系统异常
     **/
    SYSTEM_EXCEPTION(5000, "系统异常"),
    /**
     * 请求参数校验异常
     **/
    PARAM_CODE_EXCEPTION(5001, "请求参数校验异常"),
    /**
     * 请求参数解析异常
     **/
    PARAMETER_PARSE_EXCEPTION(5002, "请求参数解析异常"),
    /**
     * HTTP内容类型异常
     **/
    HTTP_MEDIA_TYPE_EXCEPTION(5003, "HTTP内容类型异常"),
    /**
     * 系统处理异常
     **/
    SPRING_BOOT_PLUS_EXCEPTION(5100, "系统处理异常"),
    /**
     * 业务处理异常
     **/
    BUSINESS_EXCEPTION(5101, "业务处理异常"),
    /**
     * 数据库处理异常
     **/
    DAO_EXCEPTION(5102, "数据库处理异常"),
    /**
     * 验证码校验异常
     **/
    VERIFICATION_CODE_EXCEPTION(5103, "验证码校验异常"),
    /**
     * 登录授权异常
     **/
    AUTHENTICATION_EXCEPTION(5104, "登录授权异常"),
    /**
     * 没有访问权限
     **/
    UNAUTHENTICATED_EXCEPTION(5105, "没有访问权限"),
    /**
     * 没有访问权限
     **/
    UNAUTHORIZED_EXCEPTION(5106, "没有访问权限"),
    /**
     * JWT Token解析异常
     **/
    JWT_DECODE_EXCEPTION(5107, "Token解析异常"),

    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION(5108, "METHOD NOT SUPPORTED"),
    /**
     * 验证码异常
     */
    CAPTCHA_CODE_EXCEPTION(5109, "验证码校验异常"),
    /**
     * 验证码过期
     */
    CAPTCHA_EXPIRE_CODE_EXCEPTION(5110, "验证码过期"),
    /**
     * redis异常
     */
    REDIS_CODE_EXCEPTION(5111, "redis异常"),
    /**
     * 工具类处理异常
     */
    UTIL_CODE_EXCEPTION(5112, "工具类处理异常"),
    /**
     * 用户名不匹配
     */
    USERNAME_NOT_FOUND_CODE_EXCEPTION(5113, "用户名不匹配"),
    /**
     * 数据异常
     */
    DATA_ACCESS_CODE_EXCEPTION(5114, "数据异常"),
    ;


    private final Integer code;
    private final String message;

    private CommonEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return this.code;
    }
}