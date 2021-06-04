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
import com.space.cornerstone.system.domain.entity.SysDept;
import com.space.cornerstone.system.domain.vo.SysDeptTreeVo;
import com.space.cornerstone.system.service.SysDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 部门接口
 *
 * @author chen qi
 * @date 2021-05-31 15:40
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/dept")
public class SysDeptController extends BaseController {

    private final SysDeptService sysDeptService;


    /**
     * 部门列表树查询
     * @apiNote 搜索时列表结构
     * @author cqmike
     * @since 2021/6/4 14:14
     * @return
     */
    @Log(name = "部门列表树查询", type = OperationLogType.LIST)
    @GetMapping("/list")
    public ReturnModel<Set<SysDeptTreeVo>> list(String name, Boolean active) {

        if (StrUtil.isEmpty(name) && active == null) {
            return ReturnModel.ok(sysDeptService.getDeptTree());
        }

        final SysDept sysDept = new SysDept();
        final LambdaQueryWrapper<SysDept> query = Wrappers.lambdaQuery(sysDept);
        final List<SysDept> list = sysDeptService.list(query);
        return ReturnModel.ok(list.stream().map(SysDeptTreeVo::convert).collect(Collectors.toSet()));
    }

    /**
     * 新增部门
     *
     * @author cqmike
     * @param sysDept
     * @since 2021/6/4 14:14
     * @return : com.space.cornerstone.framework.core.domain.model.ReturnModel<java.lang.Void>
     */
    @Log(name = "新增部门", type = OperationLogType.ADD)
    @PostMapping("/save")
    public ReturnModel<Void> saveUser(@Validated(Add.class) @RequestBody SysDept sysDept) {
        sysDeptService.saveDept(sysDept);
        return ReturnModel.ok();
    }

    /**
     * 编辑部门
     *
     * @author cqmike
     * @param sysDept
     * @since 2021/6/4 14:15
     * @return
     */
    @Log(name = "编辑部门", type = OperationLogType.UPDATE)
    @PutMapping("/update")
    public ReturnModel<Void> updateUser(@Validated(Update.class) @RequestBody SysDept sysDept) {
        sysDeptService.updateDept(sysDept);
        return ReturnModel.ok();
    }

    /**
     * 删除部门
     *
     * @author cqmike
     * @param id
     * @since 1.0.0
     * @return
     */
    @Log(name = "删除部门", type = OperationLogType.DELETE)
    @DeleteMapping("/delete/{id}")
    public ReturnModel<Void> delete(@PathVariable("id") Long id) {
        sysDeptService.deleteById(id);
        return ReturnModel.ok();
    }
}
