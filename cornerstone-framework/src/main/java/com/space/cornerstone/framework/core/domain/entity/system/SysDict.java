package com.space.cornerstone.framework.core.domain.entity.system;

import java.util.Date;

import com.space.cornerstone.framework.core.domain.entity.ActiveEntity;
import lombok.Data;

@Data
public class SysDict extends ActiveEntity {
    private static final long serialVersionUID = -9213981286819204454L;
    /**
     *
     */
    private Long id;

    /**
     * 字典编码
     */
    private String code;

    /**
     * 字典排序
     */
    private Integer sort;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典键值
     */
    private String value;

    /**
     * 样式属性（其他样式扩展）
     */
    private String cssClass;

    /**
     * 表格回显样式
     */
    private String listClass;

    /**
     * 是否默认（1是 0否）
     */
    private Boolean isDefault;

    /**
     * 备注
     */
    private String remark;
}

