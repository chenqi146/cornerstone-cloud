package com.space.cornerstone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.space.cornerstone.system.domain.entity.SysUser;
import com.space.cornerstone.system.domain.param.SysUserParam;
import com.space.cornerstone.system.domain.vo.SysUserQueryVo;
import com.space.cornerstone.system.domain.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author chen qi
 * @version 1.0.0
 * @InterfaceName SysUserMapper.java
 * @Description SysUserMapper
 * @createTime 2021年05月22日 23:40:00
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     *  查询用户授权信息
     * @param userId
     * @return
     */
    UserVo findAuthInfoByUserId(Long userId);

    /**
     * 查询用户列表
     * @param page
     * @param param
     * @return
     */
    IPage<SysUserQueryVo> findSysUserList(@Param("page") Page<SysUserQueryVo> page, @Param("param") SysUserParam param);
}
