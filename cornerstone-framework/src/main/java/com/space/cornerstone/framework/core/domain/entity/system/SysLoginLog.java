package com.space.cornerstone.framework.core.domain.entity.system;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

@Data
public class SysLoginLog {
    /**
     * 主键
     */
    private Long id;

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * IP
     */
    private String ip;

    /**
     * 区域
     */
    private String area;

    /**
     * 运营商
     */
    private String operator;

    /**
     * tokenMd5值
     */
    private String token;

    /**
     * 1:登录，2：登出
     */
    private Integer type;

    /**
     * 是否成功 true:成功/false:失败
     */
    private Boolean success;

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 失败消息记录
     */
    private String exceptionMessage;

    /**
     * 浏览器名称
     */
    private String userAgent;

    /**
     * 浏览器名称
     */
    private String browserName;

    /**
     * 浏览器版本
     */
    private String browserVersion;

    /**
     * 浏览器引擎名称
     */
    private String engineName;

    /**
     * 浏览器引擎版本
     */
    private String engineVersion;

    /**
     * 系统名称
     */
    private String osName;

    /**
     * 平台名称
     */
    private String platformName;

    /**
     * 是否是手机,0:否,1:是
     */
    private Boolean mobile;

    /**
     * 移动端设备名称
     */
    private String deviceName;

    /**
     * 移动端设备型号
     */
    private String deviceModel;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}

