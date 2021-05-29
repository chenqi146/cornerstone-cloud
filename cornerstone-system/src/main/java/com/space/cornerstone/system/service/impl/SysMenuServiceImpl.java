package com.space.cornerstone.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.service.impl.BaseServiceImpl;
import com.space.cornerstone.system.domain.entity.SysMenu;
import com.space.cornerstone.system.domain.entity.SysUser;
import com.space.cornerstone.system.domain.vo.SysMenuTreeVo;
import com.space.cornerstone.system.mapper.SysMenuMapper;
import com.space.cornerstone.system.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    /**
     * @return : java.util.Set<com.space.cornerstone.system.domain.vo.SysMenuTreeVo>
     * @throws
     * @Description 获取菜单树
     * @author chen qi
     * @since 2021/5/28 14:36
     */
    @Override
    public Set<SysMenuTreeVo> getMenuTree() {

        List<SysMenu> menuList = this.list();

        Map<Long, SysMenuTreeVo> convertMap = menuList.stream().collect(Collectors.toMap(SysMenu::getId, m -> {
            SysMenuTreeVo vo = new SysMenuTreeVo();
            BeanUtil.copyProperties(m, vo);
            return vo;
        }, (k1, k2) -> k1));

        for (SysMenu menu : menuList) {
            final SysMenuTreeVo parent = convertMap.get(menu.getParentId());
            if (parent == null) {
                continue;
            }
            SysMenuTreeVo treeVo = new SysMenuTreeVo();
            BeanUtil.copyProperties(menu, treeVo);
            parent.getChildren().add(treeVo);
        }

        return convertMap.values().stream().filter(m -> Objects.equals(m.getParentId(), Constant.ROOT_ID)).collect(Collectors.toSet());
    }
}
