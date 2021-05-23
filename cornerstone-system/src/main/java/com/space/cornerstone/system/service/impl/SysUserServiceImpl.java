package com.space.cornerstone.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.cornerstone.framework.core.domain.entity.system.SysUser;
import com.space.cornerstone.framework.core.util.PreconditionsUtil;
import com.space.cornerstone.system.mapper.SysUserMapper;
import com.space.cornerstone.system.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName SysUserServiceImpl.java
 * @Description SysUserServiceImpl
 * @createTime 2021年05月23日 19:46:00
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public SysUser getByUsername(String username) {
        PreconditionsUtil.checkArgument(StrUtil.isNotEmpty(username), "username is not empty");
        LambdaQueryChainWrapper<SysUser> wrapper = lambdaQuery().eq(SysUser::getUserName, username).last("LIMIT 1");
        return getBaseMapper().selectOne(wrapper);
    }
}
