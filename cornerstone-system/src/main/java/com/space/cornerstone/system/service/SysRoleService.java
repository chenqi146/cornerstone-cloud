package com.space.cornerstone.system.service;

import com.space.cornerstone.framework.core.domain.model.Paging;
import com.space.cornerstone.framework.core.service.BaseService;
import com.space.cornerstone.system.domain.entity.SysRole;
import com.space.cornerstone.system.domain.param.SysRoleParam;

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

}
