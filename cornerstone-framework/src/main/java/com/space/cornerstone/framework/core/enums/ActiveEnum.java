package com.space.cornerstone.framework.core.enums;

import lombok.RequiredArgsConstructor;

/**
 * @author chen qi
 * @version 1.0.0
 * @EnumName ActiveEnum.java
 * @Description ActiveEnum
 * @createTime 2021年05月18日 22:43:00
 */
@RequiredArgsConstructor
public enum ActiveEnum implements BaseEnum<String> {
    Y("启用"), N("禁用");
    private final String msg;

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getMessage() {
        return name();
    }
}
