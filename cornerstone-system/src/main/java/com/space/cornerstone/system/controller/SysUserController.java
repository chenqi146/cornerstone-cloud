package com.space.cornerstone.system.controller;

import com.space.cornerstone.framework.core.annotation.Log;
import com.space.cornerstone.framework.core.controller.BaseController;
import com.space.cornerstone.framework.core.domain.model.Paging;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import com.space.cornerstone.framework.core.enums.OperationLogType;
import com.space.cornerstone.framework.core.validator.groups.Add;
import com.space.cornerstone.framework.core.validator.groups.Update;
import com.space.cornerstone.system.domain.entity.SysRole;
import com.space.cornerstone.system.domain.entity.SysUser;
import com.space.cornerstone.system.domain.param.SysRoleParam;
import com.space.cornerstone.system.domain.param.SysUserParam;
import com.space.cornerstone.system.domain.vo.SysUserQueryVo;
import com.space.cornerstone.system.service.SysRoleService;
import com.space.cornerstone.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口
 *
 * @author chen qi
 * @date 2021-05-31 15:40
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class SysUserController extends BaseController {

    private final SysUserService sysUserService;

    /**
     * 分页获取用户列表
     *
     * @param sysUserParam sysUserParam
     * @return
     * @author cqmike
     * @since 2021-06-01 23:15
     */
    @Log(name = "系统用户分页列表", type = OperationLogType.PAGE)
    @GetMapping("/list")
    public ReturnModel<Paging<SysUserQueryVo>> list(SysUserParam sysUserParam) {
        Paging<SysUserQueryVo> sysUserQueryVoPaging = sysUserService.listPage(sysUserParam);
        return ReturnModel.ok(sysUserQueryVoPaging);
    }

    /**
     * 新增用户
     *
     * @param sysUser
     * @return : com.space.cornerstone.framework.core.domain.model.ReturnModel<java.lang.Void>
     * @author cqmike
     * @since 2021/6/3 11:18
     */
    @Log(name = "新增用户", type = OperationLogType.ADD)
    @PostMapping("/save")
    public ReturnModel<Void> saveUser(@Validated(Add.class) @RequestBody SysUser sysUser) {
        sysUserService.saveSysUser(sysUser);
        return ReturnModel.ok();
    }
    /**
     * 新增用户
     *
     * @param username
     * @return : com.space.cornerstone.framework.core.domain.model.ReturnModel<java.lang.Void>
     * @author cqmike
     * @since 2021/6/3 11:18
     */
    @Log(name = "查询用户详情", type = OperationLogType.QUERY)
    @PostMapping("/get/{username}")
    public ReturnModel<SysUser> get(@PathVariable("username") String username) {
        return ReturnModel.ok(sysUserService.getByUsername(username).setPassword(null));
    }

    /**
     * 编辑用户
     *
     * @param sysUser
     * @return : com.space.cornerstone.framework.core.domain.model.ReturnModel<java.lang.Void>
     * @author cqmike
     * @since 2021/6/3 11:20
     */
    @Log(name = "编辑用户", type = OperationLogType.UPDATE)
    @PutMapping("/update")
    public ReturnModel<Void> updateUser(@Validated(Update.class) @RequestBody SysUser sysUser) {
        sysUserService.updateSysUser(sysUser);
        return ReturnModel.ok();
    }

    /**
     * 删除用户
     *
     * @param id
     * @return : com.space.cornerstone.framework.core.domain.model.ReturnModel<java.lang.Void>
     * @author cqmike
     * @since 2021/6/3 11:21
     */
    @Log(name = "删除用户", type = OperationLogType.DELETE)
    @DeleteMapping("/delete/{id}")
    public ReturnModel<Void> delete(@PathVariable("id") Long id) {
        sysUserService.deleteById(id);
        return ReturnModel.ok();
    }


    /**
     * 重置密码
     *
     * @author cqmike
     * @param id
     * @param newPassword
     * @since 1.0.0
     * @return
     */
    @Log(name = "重置密码", type = OperationLogType.UPDATE)
    @DeleteMapping("/resetPassword")
    public ReturnModel<Void> resetPassword(Long id, String newPassword) {
        sysUserService.resetPassword(id, newPassword);
        return ReturnModel.ok();
    }

}
