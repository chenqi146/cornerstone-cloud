package com.space.cornerstone.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.cornerstone.framework.core.service.impl.BaseServiceImpl;
import com.space.cornerstone.system.domain.entity.SysMenu;
import com.space.cornerstone.system.domain.entity.SysUser;
import com.space.cornerstone.system.mapper.SysMenuMapper;
import com.space.cornerstone.system.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName SysMenuServiceImpl.java
 * @Description SysMenuServiceImpl
 * @createTime 2021年05月23日 20:19:00
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    /**
     * @param sysUser 用户信息
     * @return : java.util.Set<java.lang.String>
     * @Description 根据用户获取菜单权限
     * @author chen qi
     * @since 2021-05-23 19:21
     */
    @Override
    public Set<String> getMenuPermission(SysUser sysUser) {

        if (sysUser.isAdmin()) {
            return CollUtil.newHashSet("*:*:*");
        }

        List<SysMenu> menuList = getBaseMapper().findMenuByUserId(sysUser.getId());
        return menuList.stream().map(SysMenu::getCode).collect(Collectors.toSet());
    }
}
