package com.space.cornerstone.framework.core.domain.entity;

import com.space.cornerstone.framework.core.enums.ActiveEnum;
import lombok.Data;

/**
 * @author chenqi
 * @version 1.0.0
 * @ClassName ActiveEntity.java
 * @Description ActiveEntity
 * @createTime 2021年05月18日 08:33:00
 */
@Data
public class ActiveEntity extends LocalEntity{
    private static final long serialVersionUID = 3657064262310361571L;

    protected ActiveEnum active;


}
