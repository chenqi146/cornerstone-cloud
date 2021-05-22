package com.space.cornerstone.framework.core.domain.entity;

import lombok.Data;

/**
 * @author chenqi
 * @version 1.0.0
 * @ClassName ActiveEntity.java
 * @Description ActiveEntity
 * @createTime 2021年05月18日 08:33:00
 */
@Data
public class ActiveEntity extends LocalEntity {
    private static final long serialVersionUID = 3657064262310361571L;

    /**
     * 启用/停用  Y-启用
     */
    protected Boolean active;

}
