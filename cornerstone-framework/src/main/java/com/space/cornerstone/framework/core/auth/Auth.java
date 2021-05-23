package com.space.cornerstone.framework.core.auth;

import cn.hutool.core.map.MapUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.space.cornerstone.framework.core.domain.model.AuthUser;
import com.space.cornerstone.framework.core.util.JacksonUtil;

import java.util.Map;

/**
 * @program: common
 * @description: 用户私有变量
 * @author: chen qi
 * @create: 2020-11-08 08:07
 **/
public final class Auth {

    private static final ThreadLocal<Map<String, Object>> map = new InheritableThreadLocal<>();

    public static final String USER = "user";

    public static <T> T get(String key, Class<T> tClass) {
        Map<String, Object> objectMap = map.get();
        Object v = objectMap.get(key);
        return JacksonUtil.parse(JacksonUtil.toJson(v), tClass);
    }

    public static <T> T get(String key, TypeReference<T> type) {
        Map<String, Object> objectMap = map.get();
        Object v = objectMap.get(key);
        return JacksonUtil.parse(JacksonUtil.toJson(v), type);
    }

    public static void put(String key, Object v) {
        Map<String, Object> objectMap = map.get();
        objectMap.put(key, v);
    }

    public static Object remove(String key) {
        Map<String, Object> objectMap = map.get();
        return objectMap.remove(key);
    }

    public static void clear() {
        map.remove();
    }

    public static AuthUser getUser() {
        Map<String, Object> objectMap = map.get();
        return MapUtil.get(objectMap, USER, AuthUser.class);
    }

    public static void setUser(AuthUser authUser) {
        Map<String, Object> objectMap = map.get();
        objectMap.put(USER, authUser);
    }

}
