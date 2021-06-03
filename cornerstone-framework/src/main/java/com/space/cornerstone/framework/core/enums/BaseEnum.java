package com.space.cornerstone.framework.core.enums;

/**
 * @author chen qi
 * @version 1.0.0
 * @InterfaceName BaseEnum.java
 * @Description BaseEnum
 * @createTime 2021年05月18日 08:38:00
 */
public interface BaseEnum<T> {

    /**
     * 获取标识  可在controller直接转换
     * @author chen qi
     */
    T getCode();

    /**
     *  获取枚举 描述
     * @author chen qi
     */
    String getMessage();

}
