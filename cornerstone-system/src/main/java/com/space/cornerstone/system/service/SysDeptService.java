package com.space.cornerstone.system.service;

import com.space.cornerstone.framework.core.service.BaseService;
import com.space.cornerstone.system.domain.entity.SysDept;
import com.space.cornerstone.system.domain.vo.SysDeptTreeVo;

import java.util.Set;

/**
 * @author chen qi
 * @version 1.0.0
 * @InterfaceName SysDeptService.java
 * @Description SysDeptService
 * @createTime 2021年05月23日 09:29:00
 */
public interface SysDeptService extends BaseService<SysDept> {

    /**
     * 获取部门树
     *
     * @author cqmike
     * @since 2021/6/3 11:34
     * @return : java.util.Set<com.space.cornerstone.system.domain.vo.SysDeptTreeVo>
     */
    Set<SysDeptTreeVo> getDeptTree();


}
