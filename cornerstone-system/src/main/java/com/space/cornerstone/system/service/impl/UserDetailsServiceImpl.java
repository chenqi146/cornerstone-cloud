package com.space.cornerstone.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.space.cornerstone.framework.core.domain.model.LoginUserDto;
import com.space.cornerstone.system.domain.entity.SysUser;
import com.space.cornerstone.framework.core.domain.model.AuthUser;
import com.space.cornerstone.framework.core.util.PreconditionsUtil;
import com.space.cornerstone.system.service.SysMenuService;
import com.space.cornerstone.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * 用户验证处理
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SysUserService userService;
    private final SysMenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = userService.getByUsername(username);
        Optional.ofNullable(user).orElseThrow(() -> new UsernameNotFoundException(StrUtil.format("当前用户({})不存在", username)));

        PreconditionsUtil.checkArgument(Objects.equals(user.getDeleteFlag(), Boolean.FALSE), "当前用户({})已被删除", username);
        PreconditionsUtil.checkArgument(Objects.equals(user.getActive(), Boolean.TRUE), "当前用户({})已被停用", username);

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user) {
        LoginUserDto dto = new LoginUserDto();
        BeanUtil.copyProperties(user, dto, true);
        return new AuthUser(dto, menuService.getMenuPermission(user));
    }
}
