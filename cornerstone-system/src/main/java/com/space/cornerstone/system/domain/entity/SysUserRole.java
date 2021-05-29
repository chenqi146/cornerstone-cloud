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
    * 用户和角色关联表
    */
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper=true)
public class SysUserRole extends BaseEntity {
    private static final long serialVersionUID = 5714302104378598227L;
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;
}