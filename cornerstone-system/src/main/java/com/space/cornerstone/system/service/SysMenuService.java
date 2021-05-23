package com.space.cornerstone.system.service;

import com.space.cornerstone.framework.core.domain.entity.system.SysMenu;
import com.space.cornerstone.framework.core.domain.entity.system.SysUser;
import com.space.cornerstone.framework.core.service.BaseService;
import org.apache.catalina.User;

import java.util.Set;

/**
 * @author chen qi
 * @version 1.0.0
 * @InterfaceName SysMenuService.java
 * @Description SysMenuService
 * @createTime 2021年05月23日 09:29:00
 */
public interface SysMenuService extends BaseService<SysMenu> {

    /**
     * @Description 根据用户获取菜单权限
     * @author chen qi
     * @param sysUser 用户信息
     * @since 2021-05-23 19:21
     * @return : java.util.Set<java.lang.String>
     */
    Set<String> getMenuPermission(SysUser sysUser);
}
