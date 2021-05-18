package com.space.cornerstone.framework.core.enums;

/**
 * @author chen qi
 * @version 1.0.0
 * @InterfaceName BaseEnum.java
 * @Description TODO
 * @createTime 2021年05月18日 08:38:00
 */
public interface BaseEnum<T> {

    /**
     * @title getCode
     * @description
     * @author chen qi
     * @updateTime 2021-05-18 8:41
     * @return: T
     * @throws
     */
    T getCode();

    /**
     * @title getMsg
     * @description  获取枚举 描述
     * @author chen qi
     * @updateTime 2021-05-18 8:42
     * @return: java.lang.String
     * @throws
     */
    String getMsg();

}
