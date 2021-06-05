package com.space.cornerstone.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.space.cornerstone.framework.core.domain.model.PageInfo;
import com.space.cornerstone.framework.core.domain.model.Paging;
import com.space.cornerstone.framework.core.service.impl.BaseServiceImpl;
import com.space.cornerstone.framework.core.util.PreconditionsUtil;
import com.space.cornerstone.system.domain.entity.SysRole;
import com.space.cornerstone.system.domain.entity.SysRoleMenu;
import com.space.cornerstone.system.domain.param.SysRoleParam;
import com.space.cornerstone.system.domain.param.SysRoleUpdateParam;
import com.space.cornerstone.system.mapper.SysRoleMapper;
import com.space.cornerstone.system.service.SysRoleMenuService;
import com.space.cornerstone.system.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName SysRoleServiceImpl.java
 * @Description SysRoleServiceImpl
 * @createTime 2021年05月24日 20:29:00
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysRoleMenuService sysRoleMenuService;

    /**
     * 获取角色分页列表
     *
     * @param param
     * @author cqmike
     * @since 2021/6/3 12:48
     */
    @Override
    public Paging<SysRole> listPage(SysRoleParam param) {
        Page<SysRole> page = new PageInfo<>(param, OrderItem.desc(getLambdaColumn(SysRole::getCreateTime)));

        LambdaQueryWrapper<SysRole> wrapper = Wrappers.lambdaQuery(new SysRole().setCode(param.getCode()))
                .lt(param.getEndTime() != null, SysRole::getCreateTime, param.getEndTime())
                .gt(param.getBeginTime() != null, SysRole::getCreateTime, param.getBeginTime());

        IPage<SysRole> rolePage = this.page(page, wrapper);
        return Paging.<SysRole>builder().build(rolePage);
    }


    /**
     * 编辑或新增角色
     *
     * @param param
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    @Override
    public void saveOrUpdateRole(SysRoleUpdateParam param) {
        SysRole sysRole = param.getSysRole();

        this.saveOrUpdate(sysRole);


        Long roleId = sysRole.getId();
        if (roleId != null) {
            sysRoleMenuService.remove(Wrappers.lambdaUpdate(SysRoleMenu.class).eq(SysRoleMenu::getRoleId, roleId));
        }

        Set<SysRoleMenu> sysRoleMenus = param.getSysRoleMenus();
        sysRoleMenus.forEach(rm -> rm.setRoleId(roleId));
        sysRoleMenuService.saveBatch(sysRoleMenus);
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            return;
        }

        this.removeById(id);
        sysRoleMenuService.remove(Wrappers.lambdaUpdate(SysRoleMenu.class).eq(SysRoleMenu::getRoleId, id));
    }
}
