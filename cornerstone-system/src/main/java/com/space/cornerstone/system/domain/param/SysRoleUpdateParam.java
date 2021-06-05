package com.space.cornerstone.system.domain.param;

import com.space.cornerstone.system.domain.entity.SysRole;
import com.space.cornerstone.system.domain.entity.SysRoleMenu;
import lombok.Data;

import java.util.Set;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName SysRoleUpdateParam.java
 * @Description 角色新增和修改
 * @createTime 2021年06月05日 10:36:00
 */
@Data
public class SysRoleUpdateParam {

    private SysRole sysRole;
    private Set<SysRoleMenu> sysRoleMenus;
}
