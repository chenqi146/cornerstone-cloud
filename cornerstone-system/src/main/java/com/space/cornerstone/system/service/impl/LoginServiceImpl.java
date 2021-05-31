package com.space.cornerstone.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.space.cornerstone.framework.core.auth.Auth;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.domain.entity.system.SysLoginLog;
import com.space.cornerstone.framework.core.domain.model.AuthUser;
import com.space.cornerstone.framework.core.domain.model.LoginUserDto;
import com.space.cornerstone.framework.core.exception.BusinessException;
import com.space.cornerstone.framework.core.exception.CaptchaException;
import com.space.cornerstone.framework.core.exception.CaptchaExpireException;
import com.space.cornerstone.framework.core.redis.RedisClient;
import com.space.cornerstone.framework.core.util.IpUtil;
import com.space.cornerstone.system.domain.entity.SysUser;
import com.space.cornerstone.system.service.LoginService;
import com.space.cornerstone.system.service.SysUserService;
import com.space.cornerstone.system.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName LoginServiceImpl.java
 * @Description LoginServiceImpl
 * @createTime 2021年05月25日 22:10:00
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final RedisClient redisClient;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final SysUserService sysUserService;

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

        String captchaKey = Constant.CAPTCHA_CODE + uuid;
        if (!redisClient.hasKey(captchaKey)) {
            throw new CaptchaExpireException();
        }

        String captcha = redisClient.get(captchaKey, String.class);
        if (!StrUtil.equalsIgnoreCase(code, captcha)) {
            throw new CaptchaException();
        }
        final LambdaUpdateWrapper<SysUser> lambda = new UpdateWrapper<SysUser>().lambda().eq(SysUser::getUserName, username);

        String lockKey = Constant.USER_LOCK_KEY + StrUtil.COLON + username;

        // 用户验证  停用/删除/不存在都在UserDetailsServiceImpl中做了校验
        Authentication authentication;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                final long lockNum = redisClient.incr(lockKey);
                redisClient.expire(lockKey, Constant.USER_LOCK_TIME, TimeUnit.MINUTES);
                if (lockNum > Constant.USER_LOCK_ERROR_LIMIT) {
                    sysUserService.update(lambda.set(SysUser::getLockFlag, Boolean.TRUE));
                }
                throw new BusinessException("用户或密码不正确");
            } else {
                log.error("用户登录异常, username: {} ,error: ", username, e);
                throw new BusinessException(e.getMessage());
            }
        }
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        Auth.setUser(authUser);

        // is locked, db is lock and redis has key
        final LoginUserDto userDto = authUser.getUser();
        if (Objects.equals(userDto.getLockFlag(), Boolean.TRUE) && redisClient.hasKey(lockKey)) {
            throw new BusinessException(StrUtil.format("当前用户已被锁定,请{}分钟后再试!", Constant.USER_LOCK_TIME));
        }

        // update lock status
        String requestIp = IpUtil.getRequestIp();
        lambda.set(SysUser::getLoginIp, requestIp).set(SysUser::getLockFlag, Boolean.FALSE).set(SysUser::getLoginDate, LocalDateTime.now());
        sysUserService.update(lambda);

        userDto.setLoginDate(LocalDateTime.now());
        userDto.setLoginIp(requestIp);
        userDto.setLockFlag(Boolean.FALSE);
        authUser.setUser(userDto);
        return tokenService.createToken(authUser);
    }

}
