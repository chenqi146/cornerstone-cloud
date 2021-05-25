package com.space.cornerstone.system.domain.vo;

import com.space.cornerstone.system.domain.entity.SysMenu;
import com.space.cornerstone.system.domain.entity.SysRole;
import com.space.cornerstone.system.domain.entity.SysUser;
import lombok.Data;

import java.util.Set;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName UserVo.java
 * @Description TODO
 * @createTime 2021年05月24日 20:42:00
 */
@Data
public class UserVo {

    private SysUser user;
    private Set<SysMenu> menus;
    private Set<SysRole> roles;

}
