package com.space.cornerstone.system.service;

import com.space.cornerstone.system.domain.entity.SysUser;
import com.space.cornerstone.framework.core.domain.model.Paging;
import com.space.cornerstone.framework.core.service.BaseService;
import com.space.cornerstone.system.domain.param.SysUserParam;
import com.space.cornerstone.system.domain.vo.SysUserQueryVo;
import com.space.cornerstone.system.domain.vo.UserVo;

/**
 * @author chen qi
 * @version 1.0.0
 * @InterfaceName SysUserService.java
 * @Description SysUserService
 * @createTime 2021年05月23日 09:29:00
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    SysUser getByUsername(String username);

    /**
     * @Description 查询用户授权信息
     * @author chen qi
     * @param userId
     * @since 2021-05-24 22:18
     * @return : com.space.cornerstone.system.domain.vo.UserVo
     * @throws com.space.cornerstone.framework.core.exception.BusinessException
     */
    UserVo findAuthInfoByUserId(Long userId);

    /**
     * @Description  查询用户列表 分页
     * @author chen qi 
     * @param param
     * @since 2021-05-24 23:06 
     * @return : com.space.cornerstone.framework.core.domain.model.Paging<com.space.cornerstone.system.domain.entity.SysUser>
     */
    Paging<SysUserQueryVo> listPage(SysUserParam param);

    /**
     * @Description 新增用户 用户名,手机号,邮箱唯一
     * @author chen qi
     * @param sysUser
     * @since 2021-05-29 10:29
     * @throws com.space.cornerstone.framework.core.exception.BusinessException
     */
    void saveSysUser(SysUser sysUser);

    /**
     * @Description 编辑用户 用户名,手机号,邮箱唯一
     * @author chen qi
     * @param sysUser
     * @since 2021-05-29 10:29
     * @throws com.space.cornerstone.framework.core.exception.BusinessException
     */
    void updateSysUser(SysUser sysUser);

    /**
     * @Description  修改密码
     * @author chen qi
     * @param id
     * @param oldPassword
     * @param newPassword
     * @since 2021-05-29 10:58
     */
    void updatePassword(Long id, String oldPassword, String newPassword);


}
