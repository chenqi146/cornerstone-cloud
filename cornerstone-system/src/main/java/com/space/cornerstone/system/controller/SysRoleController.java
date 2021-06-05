package com.space.cornerstone.system.controller;

import com.space.cornerstone.framework.core.annotation.Log;
import com.space.cornerstone.framework.core.controller.BaseController;
import com.space.cornerstone.framework.core.domain.model.Paging;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import com.space.cornerstone.framework.core.enums.OperationLogType;
import com.space.cornerstone.framework.core.validator.groups.Add;
import com.space.cornerstone.framework.core.validator.groups.Update;
import com.space.cornerstone.system.domain.entity.SysRole;
import com.space.cornerstone.system.domain.param.SysRoleParam;
import com.space.cornerstone.system.domain.param.SysRoleUpdateParam;
import com.space.cornerstone.system.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户接口
 *
 * @author chen qi
 * @date 2021-05-31 15:40
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class SysRoleController extends BaseController {

    private final SysRoleService sysRoleService;


    /**
     * 角色列表查询
     * @apiNote 搜索时列表结构
     * @author cqmike
     * @since 2021/6/4 14:14
     * @return
     */
    @Log(name = "角色列表查询", type = OperationLogType.LIST)
    @GetMapping("/list")
    public ReturnModel<Paging<SysRole>> list(SysRoleParam param) {

        final Paging<SysRole> list = sysRoleService.listPage(param);
        return ReturnModel.ok(list);
    }

    /**
     * 新增角色
     *
     * @author cqmike
     * @param param
     * @since 2021/6/4 14:14
     * @return : com.space.cornerstone.framework.core.domain.model.ReturnModel<java.lang.Void>
     */
    @Log(name = "新增角色", type = OperationLogType.ADD)
    @PostMapping("/save")
    public ReturnModel<Void> saveUser(@Validated(Add.class) @RequestBody SysRoleUpdateParam param) {
        sysRoleService.saveOrUpdateRole(param);
        return ReturnModel.ok();
    }


    /**
     * 查询角色详情
     *
     * @author cqmike
     * @param id
     * @since 1.0.0
     * @return
     */
    @Log(name = "查询角色详情", type = OperationLogType.QUERY)
    @PostMapping("/get/{id}")
    public ReturnModel<SysRole> get(@PathVariable("id") Long id) {
        return ReturnModel.ok(sysRoleService.getById(id));
    }

    /**
     * 编辑角色
     *
     * @author cqmike
     * @param param
     * @since 2021/6/4 14:15
     * @return
     */
    @Log(name = "编辑角色", type = OperationLogType.UPDATE)
    @PutMapping("/update")
    public ReturnModel<Void> updateUser(@Validated(Update.class) @RequestBody SysRoleUpdateParam param) {
        sysRoleService.saveOrUpdateRole(param);
        return ReturnModel.ok();
    }

    /**
     * 删除角色
     *
     * @author cqmike
     * @param id
     * @since 1.0.0
     * @return
     */
    @Log(name = "删除角色", type = OperationLogType.DELETE)
    @DeleteMapping("/delete/{id}")
    public ReturnModel<Void> delete(@PathVariable("id") Long id) {
        sysRoleService.deleteById(id);
        return ReturnModel.ok();
    }
}
