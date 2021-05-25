package com.space.cornerstone.system.domain.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName LoginParam.java
 * @Description 登录参数
 * @createTime 2021年05月25日 08:35:00
 */
@Data
public class LoginParam implements Serializable {

    private static final long serialVersionUID = 4845773076495026507L;
    /**
     * 登录名
     */
    private String s;
    /**
     * 加密后的密码
     */
    private String d;

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证码唯一标识
     */
    private String uuid;
}
