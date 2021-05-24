package com.space.cornerstone.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.cornerstone.framework.core.domain.entity.system.SysUser;
import com.space.cornerstone.framework.core.domain.model.PageInfo;
import com.space.cornerstone.framework.core.domain.model.Paging;
import com.space.cornerstone.framework.core.exception.BusinessException;
import com.space.cornerstone.framework.core.util.PreconditionsUtil;
import com.space.cornerstone.system.domain.param.SysUserParam;
import com.space.cornerstone.system.domain.vo.SysUserQueryVo;
import com.space.cornerstone.system.domain.vo.UserVo;
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

    /**
     * @param userId
     * @return : com.space.cornerstone.system.domain.vo.UserVo
     * @Description 查询用户授权信息
     * @author chen qi
     * @since 2021-05-24 22:18
     * @throws BusinessException
     */
    @Override
    public UserVo findAuthInfoByUserId(Long userId) {
        PreconditionsUtil.checkArgument(userId != null, "userId is not empty");
        return getBaseMapper().findAuthInfoByUserId(userId);
    }

    /**
     * 查询用户列表 分页
     * @param param
     * @return : com.space.cornerstone.framework.core.domain.model.Paging<com.space.cornerstone.framework.core.domain.entity.system.SysUser>
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
}
