package com.space.cornerstone.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.space.cornerstone.framework.core.domain.model.PageInfo;
import com.space.cornerstone.framework.core.domain.model.Paging;
import com.space.cornerstone.framework.core.service.impl.BaseServiceImpl;
import com.space.cornerstone.system.domain.entity.SysRole;
import com.space.cornerstone.system.domain.param.SysRoleParam;
import com.space.cornerstone.system.mapper.SysRoleMapper;
import com.space.cornerstone.system.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    /**
     * 获取角色分页列表
     *
     * @param sysRoleParam
     * @author cqmike
     * @since 2021/6/3 12:48
     */
    @Override
    public Paging<SysRole> listPage(SysRoleParam sysRoleParam) {
        Page<SysRole> page = new PageInfo<>(sysRoleParam, OrderItem.desc(getLambdaColumn(SysRole::getCreateTime)));
        IPage<SysRole> rolePage = this.page(page);
        return Paging.<SysRole>builder().build(rolePage);
    }
}
