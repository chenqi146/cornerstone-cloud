package com.space.cornerstone.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.exception.BusinessException;
import com.space.cornerstone.framework.core.service.impl.BaseServiceImpl;
import com.space.cornerstone.framework.core.util.PreconditionsUtil;
import com.space.cornerstone.system.constant.SysConstant;
import com.space.cornerstone.system.domain.entity.SysMenu;
import com.space.cornerstone.system.domain.entity.SysUser;
import com.space.cornerstone.system.domain.vo.SysMenuTreeVo;
import com.space.cornerstone.system.enums.MenuType;
import com.space.cornerstone.system.mapper.SysMenuMapper;
import com.space.cornerstone.system.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
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

        Map<Long, SysMenuTreeVo> convertMap = menuList.stream()
                .collect(Collectors.toMap(SysMenu::getId, SysMenuTreeVo::convert, (k1, k2) -> k1));

        for (SysMenu menu : menuList) {
            final SysMenuTreeVo parent = convertMap.get(menu.getParentId());
            if (parent == null) {
                continue;
            }
            SysMenuTreeVo treeVo = SysMenuTreeVo.convert(menu);
            parent.getChildren().add(treeVo);
        }

        return convertMap.values().stream().filter(Objects::nonNull)
                .filter(m -> Objects.equals(m.getParentId(), Constant.ROOT_ID)).collect(Collectors.toSet());
    }

    /**
     * 新增菜单
     *
     * @param sysMenu
     * @author cqmike
     * @since 2021/6/3 11:44
     */
    @Override
    public void saveMenu(SysMenu sysMenu) {
        checkMenu(sysMenu);
        this.save(sysMenu);
    }

    /**
     * 校验菜单参数
     *
     * @author cqmike
     * @param sysMenu
     * @since 2021/6/3 13:01
     */
    private void checkMenu(SysMenu sysMenu) {
        final Long parentId = sysMenu.getParentId();
        if (parentId != null && parentId > 0) {
            final SysMenu parent = Optional.ofNullable(this.getById(parentId)).orElseThrow(() -> new BusinessException("此父级菜单已经被删除"));
            sysMenu.setLevel(parent.getLevel());
        } else {
            sysMenu.setParentId(Constant.ROOT_ID);
            sysMenu.setLevel(SysConstant.LEVEL_ROOT);
        }

        if (!MenuType.isButton(sysMenu.getMenuType())) {
            PreconditionsUtil.checkArgument(StrUtil.isNotEmpty(sysMenu.getUrl()), "路由地址不能为空");
        }
    }

    /**
     * 编辑菜单
     *
     * @param sysMenu
     * @author cqmike
     * @since 2021/6/3 11:44
     */
    @Override
    public void updateMenu(SysMenu sysMenu) {
        checkMenu(sysMenu);
        this.updateById(sysMenu);
    }

    /**
     * 根据id删除菜单及其子菜单
     *
     * @param id
     * @author cqmike
     * @since 2021/6/3 18:28
     */
    @Override
    public void deleteById(Long id) {

        if (id == null) {
            return;
        }

        final SysMenu sysMenu = this.getById(id);
        if (sysMenu == null) {
            return;
        }

        final List<SysMenu> menuList = this.list();

        List<Long> allIdList = CollUtil.newArrayList();
        collectTreeIdList(allIdList, menuList, id);

    }

    /**
     * 获取当前节点的所有子节点id
     *
     * @author cqmike
     * @param allIdList
     * @param menuList
     * @param currentId
     * @since 2021/6/3 19:03
     */
    private void collectTreeIdList(List<Long> allIdList, List<SysMenu> menuList, Long currentId) {

        if (CollUtil.isEmpty(menuList)) {
            return;
        }

    }
}
