package com.space.cornerstone.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.cornerstone.framework.core.domain.entity.system.SysRole;
import com.space.cornerstone.system.mapper.SysRoleMapper;
import com.space.cornerstone.system.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName SysRoleServiceImpl.java
 * @Description TODO
 * @createTime 2021年05月24日 20:29:00
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
}
