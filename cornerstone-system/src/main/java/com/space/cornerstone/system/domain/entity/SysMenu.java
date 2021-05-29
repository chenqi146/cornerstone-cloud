package com.space.cornerstone.system.domain.entity;

import java.util.Date;

import com.space.cornerstone.framework.core.domain.entity.ActiveEntity;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysMenu extends ActiveEntity {
    private static final long serialVersionUID = -4939639999420098762L;
    /**
     * 菜单ID
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 权限标识
     */
    private String code;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 路由地址
     */
    private String url;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 层级，1：第一级，2：第二级，N：第N级
     */
    private Integer level;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private String menuType;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;
}

