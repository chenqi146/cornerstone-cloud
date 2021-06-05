package com.space.cornerstone.system.service;

import com.space.cornerstone.framework.core.domain.model.Paging;
import com.space.cornerstone.framework.core.service.BaseService;
import com.space.cornerstone.system.domain.entity.SysRole;
import com.space.cornerstone.system.domain.entity.SysRoleMenu;
import com.space.cornerstone.system.domain.param.SysRoleParam;
import com.space.cornerstone.system.domain.param.SysRoleUpdateParam;

import java.util.Set;

/**
 * @author chen qi
 * @version 1.0.0
 * @InterfaceName SysRoleService.java
 * @Description SysRoleService
 * @createTime 2021年05月23日 09:29:00
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 获取角色分页列表
     *
     * @author cqmike
     * @param sysRoleParam
     * @since 2021/6/3 12:48
     */
    Paging<SysRole> listPage(SysRoleParam sysRoleParam);

    /**
     * 编辑或新增角色
     *
     * @author cqmike
     * @param param
     * @since 1.0.0
     * @return
     */
    void saveOrUpdateRole(SysRoleUpdateParam param);

    /**
     * 删除角色
     *
     * @author cqmike
     * @param id
     * @since 1.0.0
     * @return
     */
    void deleteById(Long id);
}
