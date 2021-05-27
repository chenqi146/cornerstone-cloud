package com.space.cornerstone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.space.cornerstone.system.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 查询用户的菜单
     * @param userId
     * @return
     */
    List<SysMenu> findMenuByUserId(Long userId);

}