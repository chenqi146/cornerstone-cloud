package com.space.cornerstone.framework.core.domain.entity.system;

import com.space.cornerstone.framework.core.domain.entity.ActiveEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SysDict extends ActiveEntity {
    private static final long serialVersionUID = -9213981286819204454L;
    /**
     *
     */
    private Long id;

    /**
     * 字典类型编码
     */
    private String code;

    /**
     * 字典类型名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    private List<SysDictData> dataList;
}

