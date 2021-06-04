package com.space.cornerstone.system.domain.vo;

import cn.hutool.core.bean.BeanUtil;
import com.space.cornerstone.framework.core.domain.entity.ActiveEntity;
import com.space.cornerstone.system.domain.entity.SysMenu;
import com.space.cornerstone.system.enums.MenuType;
import lombok.Data;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName SysMenuQueryTree.java
 * @Description 系统菜单树形列表
 * @createTime 2021年05月25日 23:01:00
 */
@Data
public class SysMenuTreeVo extends ActiveEntity {
    private static final long serialVersionUID = 2536862852930663353L;

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
    private MenuType menuType;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

    /**
     * 子菜单列表
     */
    private Set<SysMenuTreeVo> children = new TreeSet<>(Comparator.comparing(SysMenuTreeVo::getSort));


    public static SysMenuTreeVo convert(SysMenu sysMenu) {
        if (sysMenu == null) {
            return null;
        }
        final SysMenuTreeVo vo = new SysMenuTreeVo();
        BeanUtil.copyProperties(sysMenu, vo);
        return vo;
    }
}
