package com.space.cornerstone.system.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.domain.model.AuthUser;
import com.space.cornerstone.framework.core.redis.RedisClient;
import com.space.cornerstone.system.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName TokenServiceImpl.java
 * @Description TokenServiceImpl
 * @createTime 2021年05月23日 10:50:00
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TokenServiceImpl implements TokenService {

    private final RedisClient redisClient;

    @Value("${token.secret:cuAihCz53DZRjZwbsGcZJ2Ai6At=f32r3r3r23rT142uphtJMsk7iQ+}")
    private String secret;

    @Value("${token.expireTime:30}")
    private Integer expireTime;


    private String getTokenKey(String uuid) {
        return Constant.TOKEN + StrUtil.COLON + uuid;
    }

    /**
     * 清除redis用户信息
     *
     * @param token
     */
    @Override
    public void clearUser(String token) {
        if (StrUtil.isEmpty(token)) {
            return;
        }
        redisClient.del(Constant.TOKEN + StrUtil.COLON + token);
    }

    /**
     * 设置用户信息 给线程变量也设置
     *
     * @param authUser
     */
    @Override
    public void setUser(AuthUser authUser) {
        if (authUser == null) {
            return;
        }
        refreshToken(authUser);
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    @Override
    public AuthUser getUser(String token) {
        if (StrUtil.isEmpty(token)) {
            return null;
        }
        Claims claims = parseToken(token);
        String uuid = MapUtil.getStr(claims, Constant.TOKEN);
        return redisClient.get(getTokenKey(uuid), AuthUser.class);
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 生成token
     *
     * @param user
     * @return
     */
    @Override
    public String createToken(AuthUser user) {
        String token = IdUtil.fastUUID();
        user.setToken(token);
        refreshToken(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constant.TOKEN, token);
        return createToken(claims);
    }

    /**
     * 校验token
     *
     * @param authUser
     * @return
     */
    @Override
    public boolean verifyToken(AuthUser authUser) {
        long expireTime = authUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime > 20 * DateUnit.MINUTE.getMillis()) {
            return false;
        }
        refreshToken(authUser);
        return true;
    }

    /**
     * 刷新token
     *
     * @param authUser
     */
    @Override
    public void refreshToken(AuthUser authUser) {
        authUser.setExpireTime(System.currentTimeMillis() + expireTime * DateUnit.MINUTE.getMillis());
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(authUser.getToken());
        authUser.getUser().setPassword(null);
        redisClient.setex(userKey, authUser, expireTime, TimeUnit.MINUTES);
    }
}
