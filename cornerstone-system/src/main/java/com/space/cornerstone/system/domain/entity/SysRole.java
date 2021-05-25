package com.space.cornerstone.system.domain.entity;


import com.space.cornerstone.framework.core.domain.entity.ActiveEntity;
import lombok.Data;

@Data
public class SysRole extends ActiveEntity {
    private static final long serialVersionUID = 4609893906440456482L;
    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色权限字符串
     */
    private String code;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;
}

