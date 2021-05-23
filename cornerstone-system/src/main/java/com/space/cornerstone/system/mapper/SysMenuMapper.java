package com.space.cornerstone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.space.cornerstone.framework.core.domain.entity.system.SysMenu;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 查询用户的菜单
     * @param userId
     * @return
     */
    List<SysMenu> findMenuByUserId(Long userId);

}