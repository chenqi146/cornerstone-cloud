package com.space.cornerstone.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.space.cornerstone.framework.core.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
    * 角色和菜单关联表
    */
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)
public class SysRoleMenu extends BaseEntity {
    private static final long serialVersionUID = -3380364324238663629L;
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

}