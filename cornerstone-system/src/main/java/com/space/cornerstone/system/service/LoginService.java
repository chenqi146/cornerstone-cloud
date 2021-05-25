package com.space.cornerstone.system.service;

/**
 * @author chen qi
 * @version 1.0.0
 * @InterfaceName LoginService.java
 * @Description LoginService
 * @createTime 2021年05月25日 08:45:00
 */
public interface LoginService {

    /**
     * @Description 登录
     * @author chen qi
     * @param username
     * @param password
     * @param code 验证码
     * @param uuid 验证码标识
     * @since 2021-05-25 22:09
     * @return : java.lang.String
     */
    String login(String username, String password, String code, String uuid);
}
