package com.space.cornerstone.system.domain.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName CaptchaVo.java
 * @Description 验证码返回
 * @createTime 2021年05月25日 08:32:00
 */
@Data
@Builder
public class CaptchaVo {
    /**
     * 验证码标识
     */
    private String uuid;
    /**
     * 验证码图片base64
     */
    private String imgBase64;
}
