package com.space.cornerstone.system.domain.vo;

import cn.hutool.core.bean.BeanUtil;
import com.space.cornerstone.framework.core.domain.entity.ActiveEntity;
import com.space.cornerstone.system.domain.entity.SysDept;
import lombok.Data;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName SysDeptQueryTree.java
 * @Description 部门树结构
 * @createTime 2021年05月25日 22:58:00
 */
@Data
public class SysDeptTreeVo extends ActiveEntity {
    private static final long serialVersionUID = -4036772449152022205L;

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

    /**
     * 子部门列表
     */
    private Set<SysDeptTreeVo> children = new TreeSet<>(Comparator.comparing(SysDeptTreeVo::getSort));


    public static SysDeptTreeVo convert(SysDept sysDept) {
        if (sysDept == null) {
            return null;
        }
        final SysDeptTreeVo vo = new SysDeptTreeVo();
        BeanUtil.copyProperties(sysDept, vo);
        return vo;
    }
}
