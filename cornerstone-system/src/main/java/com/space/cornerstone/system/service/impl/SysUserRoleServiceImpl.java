package com.space.cornerstone.system.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.cornerstone.system.mapper.SysUserRoleMapper;
import com.space.cornerstone.system.domain.entity.SysUserRole;
import com.space.cornerstone.system.service.SysUserRoleService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService{

}
