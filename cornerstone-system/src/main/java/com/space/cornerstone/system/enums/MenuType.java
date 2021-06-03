package com.space.cornerstone.system.enums;

import com.space.cornerstone.framework.core.enums.BaseEnum;

import java.util.Objects;

/**
 * MenuType
 *
 * @author chen qi
 * @date 2021-06-03 12:53
 **/
public enum MenuType implements BaseEnum<String> {
    /**
     * 目录
     */
    DIR,
    /**
     * 菜单
     */
    MENU,
    /**
     * 按钮
     */
    BUTTON;

    /**
     * 获取标识  可在controller直接转换
     *
     * @author chen qi
     */
    @Override
    public String getCode() {
        return name();
    }

    /**
     * 获取枚举 描述
     * @author chen qi
     */
    @Override
    public String getMessage() {
        return name();
    }

    /**
     * 是否是按钮
     *
     * @author cqmike
     * @param menuType
     * @since 2021/6/3 12:59
     * @return : boolean
     */
    public static boolean isButton(MenuType menuType) {
        return Objects.equals(menuType, BUTTON);
    }
}
