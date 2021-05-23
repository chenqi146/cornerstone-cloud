package com.space.cornerstone.system.service;

import com.space.cornerstone.framework.core.domain.model.AuthUser;

/**
 * @author chen qi
 * @version 1.0.0
 * @InterfaceName TokenService.java
 * @Description TokenService
 * @createTime 2021年05月23日 09:33:00
 */
public interface TokenService {

    /**
     * 清除redis用户信息
     * @param token
     */
    void clearUser(String token);

    /**
     * 设置用户信息 给线程变量也设置
     * @param authUser
     */
    void setUser(AuthUser authUser);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    AuthUser getUser(String token);

    /**
     * 生成token
     * @param user
     * @return
     */
    String createToken(AuthUser user);

    /**
     * 校验token
     * @param authUser
     * @return
     */
    boolean verifyToken(AuthUser authUser);

    /**
     * 刷新token
     * @param authUser
     */
    void refreshToken(AuthUser authUser);
}
