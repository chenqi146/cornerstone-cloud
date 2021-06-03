package com.space.cornerstone.system.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.google.code.kaptcha.Producer;
import com.space.cornerstone.system.constant.SysConstant;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import com.space.cornerstone.framework.core.exception.CaptchaException;
import com.space.cornerstone.framework.core.redis.RedisClient;
import com.space.cornerstone.system.domain.vo.CaptchaVo;
import lombok.RequiredArgsConstructor;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码接口
 * @author cqmike
 * @version 1.0.0
 * @ClassName CaptchaController.java
 * @createTime 2021年05月25日 08:42:00
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/captcha")
public class CaptchaController {

    private final Producer captchaProducer;
    private final RedisClient redisClient;

    /**
     * 获取验证码
     * @author chen qi
     * @since 1.0.0
     * @return : com.space.cornerstone.framework.core.domain.model.ReturnModel<com.space.cornerstone.system.domain.vo.CaptchaVo>
     */
    @GetMapping("/getVerificationImage")
    public ReturnModel<CaptchaVo> getVerificationImage() {

        // 保存验证码信息
        String uuid = IdUtil.simpleUUID();
        String verifyKey = SysConstant.CAPTCHA_CODE + uuid;

        String capStr = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(capStr);

        redisClient.setex(verifyKey, capStr, SysConstant.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            throw new CaptchaException("获取验证码异常", e);
        }

        CaptchaVo build = CaptchaVo.builder()
                .uuid(uuid)
                .imgBase64(Base64.encode(os.toByteArray()))
                .build();
        return ReturnModel.ok(build);

    }
}
