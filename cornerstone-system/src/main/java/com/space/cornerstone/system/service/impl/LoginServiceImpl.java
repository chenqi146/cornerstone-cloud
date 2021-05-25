package com.space.cornerstone.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.domain.model.AuthUser;
import com.space.cornerstone.framework.core.exception.BusinessException;
import com.space.cornerstone.framework.core.exception.CaptchaException;
import com.space.cornerstone.framework.core.exception.CaptchaExpireException;
import com.space.cornerstone.framework.core.redis.RedisClient;
import com.space.cornerstone.system.service.LoginService;
import com.space.cornerstone.system.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName LoginServiceImpl.java
 * @Description LoginServiceImpl
 * @createTime 2021年05月25日 22:10:00
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final RedisClient redisClient;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    /**
     * @param username
     * @param password
     * @param code     验证码
     * @param uuid     验证码标识
     * @return : java.lang.String
     * @Description 登录
     * @author chen qi
     * @since 2021-05-25 22:09
     */
    @Override
    public String login(String username, String password, String code, String uuid) {

        // TODO: 2021-05-25 日志记录
        String captchaKey = Constant.CAPTCHA_CODE + uuid;
        if (redisClient.hasKey(captchaKey)) {
            throw new CaptchaExpireException();
        }

        String captcha = redisClient.get(captchaKey, String.class);
        if (!StrUtil.equalsIgnoreCase(code, captcha)) {
            throw new CaptchaException();
        }
        // 用户验证
        Authentication authentication;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new BusinessException("用户或密码不正确");
            } else {
                throw new BusinessException(e.getMessage());
            }
        }
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        return tokenService.createToken(authUser);
    }

}
