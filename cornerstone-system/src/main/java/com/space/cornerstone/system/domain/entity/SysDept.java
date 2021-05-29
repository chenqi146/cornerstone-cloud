package com.space.cornerstone.system.domain.entity;


import com.space.cornerstone.framework.core.domain.entity.ActiveEntity;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysDept extends ActiveEntity {
    private static final long serialVersionUID = 1030859409020401154L;

    /**
     * 部门id
     */
    private Long id;

    /**
     * 父部门id
     */
    private Long parentId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 负责人
     */
    private String leader;

}

