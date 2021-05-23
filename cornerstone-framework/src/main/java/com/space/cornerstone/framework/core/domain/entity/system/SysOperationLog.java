package com.space.cornerstone.framework.core.domain.entity.system;

import java.time.LocalDateTime;

import lombok.Data;
import org.springframework.http.HttpMethod;

@Data
public class SysOperationLog {
    /**
     * 主键
     */
    private Long id;

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 日志名称
     */
    private String name;

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
     * 全路径
     */
    private String path;

    /**
     * 模块名称
     */
    private String module;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 请求方式，GET/POST
     */
    private HttpMethod requestMethod;

    /**
     * 内容类型
     */
    private String contentType;

    /**
     * 是否是JSON请求映射参数
     */
    private Boolean requestBody;

    /**
     * 请求参数
     */
    private String param;

    /**
     * tokenMd5值
     */
    private String token;

    /**
     * 0:其它,1:新增,2:修改,3:删除,4:详情查询,5:所有列表,6:分页列表,7:其它查询,8:上传文件
     */
    private Integer type;

    /**
     * 0:失败,1:成功
     */
    private Boolean success;

    /**
     * 响应结果状态码
     */
    private Integer code;

    /**
     * 响应结果消息
     */
    private String message;

    /**
     * 异常类名称
     */
    private String exceptionName;

    /**
     * 异常信息
     */
    private String exceptionMessage;

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

