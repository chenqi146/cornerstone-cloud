package com.space.cornerstone.framework.core.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author chenqi
 * @version 1.0.0
 * @ClassName LocalEntity.java
 * @Description LocalEntity
 * @createTime 2021年05月18日 08:31:00
 */
@Data
public class LocalEntity extends BaseEntity {
    private static final long serialVersionUID = -3341043990353711649L;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;

    /**
     * 创建人登录名
     */
    @TableField(fill = FieldFill.INSERT)
    protected String createBy;

    /**
     * 修改人登录名
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected String updateBy;

    /**
     * 版本号
     */
    @Version
    protected Integer version;
}
