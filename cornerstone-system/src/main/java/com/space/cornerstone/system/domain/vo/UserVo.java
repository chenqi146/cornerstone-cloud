package com.space.cornerstone.system.domain.vo;

import com.space.cornerstone.system.domain.entity.SysRole;
import com.space.cornerstone.system.domain.entity.SysUser;
import lombok.Data;

import java.util.Set;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName UserVo.java
 * @Description auth
 * @createTime 2021年05月24日 20:42:00
 */
@Data
public class UserVo {

    /**
     * 用户信息
     */
    private SysUser user;

    /**
     * 用户菜单信息树
     */
    private Set<SysMenuTreeVo> menus;

}
