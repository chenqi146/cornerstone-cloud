package com.space.cornerstone.framework.core.domain.entity.system;

import java.util.Date;

import com.space.cornerstone.framework.core.domain.entity.LocalEntity;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysConfig extends LocalEntity {
    private static final long serialVersionUID = 4183757717870188840L;
    /**
     * 参数主键
     */
    private Long id;

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数键名
     */
    private String code;

    /**
     * 参数键值
     */
    private String value;

    /**
     * 系统内置（1是 0否）
     */
    private Boolean internal;

    /**
     * 备注
     */
    private String remark;
}
