package com.space.cornerstone.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.space.cornerstone.framework.core.annotation.Log;
import com.space.cornerstone.framework.core.controller.BaseController;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import com.space.cornerstone.framework.core.enums.OperationLogType;
import com.space.cornerstone.framework.core.validator.groups.Add;
import com.space.cornerstone.framework.core.validator.groups.Update;
import com.space.cornerstone.system.domain.entity.SysMenu;
import com.space.cornerstone.system.domain.entity.SysRole;
import com.space.cornerstone.system.domain.vo.SysMenuTreeVo;
import com.space.cornerstone.system.service.SysMenuService;
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
@RequestMapping("/menu")
public class SysMenuController extends BaseController {

    private final SysMenuService sysMenuService;


    /**
     * 菜单列表查询
     * @apiNote 搜索时列表结构
     * @author cqmike
     * @since 2021/6/4 14:14
     * @return
     */
    @Log(name = "菜单列表查询", type = OperationLogType.LIST)
    @GetMapping("/list")
    public ReturnModel<Set<SysMenuTreeVo>> list(String name, Boolean active) {

        if (StrUtil.isEmpty(name) && active == null) {
            return ReturnModel.ok(sysMenuService.getMenuTree());
        }

        final SysMenu sysMenu = new SysMenu();
        final LambdaQueryWrapper<SysMenu> query = Wrappers.lambdaQuery(sysMenu);
        final List<SysMenu> list = sysMenuService.list(query);
        return ReturnModel.ok(list.stream().map(SysMenuTreeVo::convert).collect(Collectors.toSet()));
    }


    /**
     * 查询菜单详情
     *
     * @author cqmike
     * @param id
     * @since 1.0.0
     * @return
     */
    @Log(name = "查询菜单详情", type = OperationLogType.QUERY)
    @PostMapping("/get/{id}")
    public ReturnModel<SysMenu> get(@PathVariable("id") Long id) {
        return ReturnModel.ok(sysMenuService.getById(id));
    }


    /**
     * 新增菜单
     *
     * @author cqmike
     * @param sysMenu
     * @since 2021/6/4 14:14
     * @return : com.space.cornerstone.framework.core.domain.model.ReturnModel<java.lang.Void>
     */
    @Log(name = "新增菜单", type = OperationLogType.ADD)
    @PostMapping("/save")
    public ReturnModel<Void> saveUser(@Validated(Add.class) @RequestBody SysMenu sysMenu) {
        sysMenuService.saveMenu(sysMenu);
        return ReturnModel.ok();
    }

    /**
     * 编辑菜单
     *
     * @author cqmike
     * @param sysMenu
     * @since 2021/6/4 14:15
     * @return
     */
    @Log(name = "编辑菜单", type = OperationLogType.UPDATE)
    @PutMapping("/update")
    public ReturnModel<Void> updateUser(@Validated(Update.class) @RequestBody SysMenu sysMenu) {
        sysMenuService.updateMenu(sysMenu);
        return ReturnModel.ok();
    }

    /**
     * 删除菜单
     *
     * @author cqmike
     * @param id
     * @since 1.0.0
     * @return
     */
    @Log(name = "删除菜单", type = OperationLogType.DELETE)
    @DeleteMapping("/delete/{id}")
    public ReturnModel<Void> delete(@PathVariable("id") Long id) {
        sysMenuService.deleteById(id);
        return ReturnModel.ok();
    }
}
