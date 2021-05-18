package com.space.cornerstone.framework.core.domain.entity;

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

    protected LocalDateTime createTime;
    protected LocalDateTime updateTime;
    protected String createBy;
    protected String updateBy;

}
