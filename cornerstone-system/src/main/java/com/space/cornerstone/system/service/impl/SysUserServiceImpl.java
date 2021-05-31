package com.space.cornerstone.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.domain.model.PageInfo;
import com.space.cornerstone.framework.core.domain.model.Paging;
import com.space.cornerstone.framework.core.exception.BusinessException;
import com.space.cornerstone.framework.core.service.impl.BaseServiceImpl;
import com.space.cornerstone.framework.core.util.PreconditionsUtil;
import com.space.cornerstone.system.domain.entity.SysDept;
import com.space.cornerstone.system.domain.entity.SysRole;
import com.space.cornerstone.system.domain.entity.SysUser;
import com.space.cornerstone.system.domain.entity.SysUserRole;
import com.space.cornerstone.system.domain.param.SysUserParam;
import com.space.cornerstone.system.domain.vo.SysMenuTreeVo;
import com.space.cornerstone.system.domain.vo.SysUserQueryVo;
import com.space.cornerstone.system.domain.vo.UserVo;
import com.space.cornerstone.system.mapper.SysUserMapper;
import com.space.cornerstone.system.service.SysDeptService;
import com.space.cornerstone.system.service.SysRoleService;
import com.space.cornerstone.system.service.SysUserRoleService;
import com.space.cornerstone.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName SysUserServiceImpl.java
 * @Description SysUserServiceImpl
 * @createTime 2021年05月23日 19:46:00
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysDeptService sysDeptService;
    private final SysRoleService sysRoleService;
    private final SysUserRoleService sysUserRoleService;

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public SysUser getByUsername(String username) {
        PreconditionsUtil.checkArgument(StrUtil.isNotEmpty(username), "username is not empty");
        return lambdaQuery().eq(SysUser::getUserName, username).one();
    }

    /**
     * @param userId
     * @return : com.space.cornerstone.system.domain.vo.UserVo
     * @Description 查询用户授权信息
     * @author chen qi
     * @since 2021-05-24 22:18
     */
    @Override
    public UserVo findAuthInfoByUserId(Long userId) {
        PreconditionsUtil.checkArgument(userId != null, "userId is not empty");
        final UserVo userVo = getBaseMapper().findAuthInfoByUserId(userId);
        //  convert tree

        final Set<SysMenuTreeVo> menus = userVo.getMenus();
        Map<Long, SysMenuTreeVo> convertMap = menus.stream().collect(Collectors.toMap(SysMenuTreeVo::getId, m -> m, (k1, k2) -> k1));

        for (SysMenuTreeVo menu : menus) {
            final SysMenuTreeVo parent = convertMap.get(menu.getParentId());
            if (parent == null) {
                continue;
            }
            parent.getChildren().add(menu);
        }

        final Set<SysMenuTreeVo> menuTreeVos = convertMap.values().stream().filter(m -> Objects.equals(m.getParentId(), Constant.ROOT_ID)).collect(Collectors.toSet());
        userVo.setMenus(menuTreeVos);
        return userVo;
    }

    /**
     * 查询用户列表 分页
     *
     * @param param
     * @return : com.space.cornerstone.framework.core.domain.model.Paging<com.space.cornerstone.system.domain.entity.SysUser>
     * @Description
     * @author chen qi
     * @since 2021-05-24 23:06
     */
    @Override
    public Paging<SysUserQueryVo> listPage(SysUserParam param) {
        Page<SysUserQueryVo> page = new PageInfo<>(param, OrderItem.desc(getLambdaColumn(SysUser::getCreateTime)));
        IPage<SysUserQueryVo> sysUserList = getBaseMapper().findSysUserList(page, param);
        return Paging.builder().build(sysUserList);
    }

    /**
     * @param user
     * @throws BusinessException 重复
     * @Description 校验用户名 手机号 邮箱
     * @author chen qi
     * @since 2021-05-29 11:55
     */
    private void checkUsernameOrPhoneOrEmail(SysUser user) {
        Optional.ofNullable(lambdaQuery().eq(SysUser::getEmail, user.getEmail()).one()).orElseThrow(() -> new BusinessException(StrUtil.format("邮箱({})不能重复", user.getEmail())));
        Optional.ofNullable(lambdaQuery().eq(SysUser::getUserName, user.getUserName()).one()).orElseThrow(() -> new BusinessException(StrUtil.format("用户名({})不能重复", user.getUserName())));
        Optional.ofNullable(lambdaQuery().eq(SysUser::getPhone, user.getPhone()).one()).orElseThrow(() -> new BusinessException(StrUtil.format("手机号({})不能重复", user.getPhone())));

        Long deptId = user.getDeptId();
        Optional.ofNullable(deptId).ifPresent(i -> {
            SysDept dept = Optional.ofNullable(sysDeptService.getById(deptId)).orElseThrow(() -> new BusinessException("该部门不存在"));
            PreconditionsUtil.checkArgument(Objects.equals(dept.getActive(), Boolean.TRUE), "该部门被禁用");
        });

        List<SysRole> roles = user.getRoles();
        if (CollUtil.isEmpty(roles)) {
            return;
        }

        // 校验角色
        Map<Long, SysRole> sysRoleMap = sysRoleService.list().stream().collect(Collectors.toMap(SysRole::getId, m -> m, (k1, k2) -> k1));
        for (SysRole role : roles) {
            SysRole sysRole = Optional.ofNullable(sysRoleMap.get(role.getId())).orElseThrow(() -> new BusinessException("该角色不存在"));
            PreconditionsUtil.checkArgument(Objects.equals(sysRole.getActive(), Boolean.TRUE), "该角色被禁用");
        }

    }

    /**
     * @param sysUser
     * @throws BusinessException 校验
     * @Description 新增用户 用户名,手机号,邮箱唯一
     * @author chen qi
     * @since 2021-05-29 10:29
     */
    @Override
    public void saveSysUser(SysUser sysUser) {
        checkUsernameOrPhoneOrEmail(sysUser);

        this.save(sysUser);

        List<SysRole> roles = sysUser.getRoles();
        if (CollUtil.isEmpty(roles)) {
            return;
        }

        List<SysUserRole> userRoleList = roles.stream().map(r -> new SysUserRole().setUserId(sysUser.getId()).setRoleId(r.getId())).collect(Collectors.toList());
        sysUserRoleService.saveBatch(userRoleList);
    }

    /**
     * @param sysUser
     * @throws BusinessException
     * @Description 编辑用户 用户名,手机号,邮箱唯一
     * @author chen qi
     * @since 2021-05-29 10:29
     */
    @Override
    public void updateSysUser(SysUser sysUser) {
        checkUsernameOrPhoneOrEmail(sysUser);

        SysUser update = sysUser.cloneUpdate();
        this.updateById(update);

        List<SysRole> roles = update.getRoles();
        if (CollUtil.isEmpty(roles)) {
            return;
        }

        // 删除
        LambdaUpdateChainWrapper<SysUserRole> wrapper = sysUserRoleService.lambdaUpdate().eq(SysUserRole::getUserId, sysUser.getId());
        sysUserRoleService.remove(wrapper);

        // 新增
        List<SysUserRole> userRoleList = roles.stream().map(r -> new SysUserRole().setUserId(sysUser.getId()).setRoleId(r.getId())).collect(Collectors.toList());
        sysUserRoleService.saveBatch(userRoleList);

    }

    /**
     * @param id
     * @param oldPassword
     * @param newPassword
     * @Description 修改密码
     * @author chen qi
     * @since 2021-05-29 10:58
     */
    @Override
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        PreconditionsUtil.checkArgument(id != null && StrUtil.isNotEmpty(oldPassword) && StrUtil.isNotEmpty(newPassword), "id||oldPassword||newPassword参数不能为空");
        PreconditionsUtil.checkArgument(StrUtil.equals(oldPassword, newPassword), "新密码不能与旧密码相同");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        oldPassword = encoder.encode(oldPassword);
        newPassword = encoder.encode(newPassword);

        SysUser dbUser = this.getById(id);
        PreconditionsUtil.checkArgument(StrUtil.equals(oldPassword, dbUser.getPassword()), "密码不一致");

        this.updateById(new SysUser().setId(id).setPassword(newPassword));
    }


    /**
     * 删除用户
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        PreconditionsUtil.checkArgument(id != null, "用户id不能为空");

        final SysUser user = this.getById(id);

        if (user == null) {
            return;
        }

        if (Objects.equals(user.getDeleteFlag(), Boolean.FALSE)) {
            return;
        }

        this.removeById(id);
    }
}
