package com.space.cornerstone.system.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.cornerstone.system.mapper.SysRoleMenuMapper;
import com.space.cornerstone.system.domain.entity.SysRoleMenu;
import com.space.cornerstone.system.service.SysRoleMenuService;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService{

}
