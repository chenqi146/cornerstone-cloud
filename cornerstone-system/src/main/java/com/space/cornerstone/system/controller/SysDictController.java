package com.space.cornerstone.system.controller;

import com.space.cornerstone.framework.core.annotation.Log;
import com.space.cornerstone.framework.core.controller.BaseController;
import com.space.cornerstone.framework.core.domain.entity.system.SysDict;
import com.space.cornerstone.framework.core.domain.model.Paging;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import com.space.cornerstone.framework.core.domain.param.system.SysDictParam;
import com.space.cornerstone.framework.core.enums.OperationLogType;
import com.space.cornerstone.framework.core.service.SysDictService;
import com.space.cornerstone.framework.core.validator.groups.Add;
import com.space.cornerstone.framework.core.validator.groups.Update;
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
@RequestMapping("/dict")
public class SysDictController extends BaseController {

    private final SysDictService sysDictService;


    /**
     * 字典列表查询
     * @apiNote 搜索时列表结构
     * @author cqmike
     * @since 2021/6/4 14:14
     * @return
     */
    @Log(name = "字典列表查询", type = OperationLogType.LIST)
    @GetMapping("/list")
    public ReturnModel<Paging<SysDict>> list(SysDictParam param) {

        final Paging<SysDict> list = sysDictService.listPage(param);
        return ReturnModel.ok(list);
    }

    /**
     * 新增字典
     *
     * @author cqmike
     * @param code
     * @since 2021/6/4 14:14
     * @return : com.space.cornerstone.framework.core.domain.model.ReturnModel<java.lang.Void>
     */
    @Log(name = "查询字典详情", type = OperationLogType.QUERY)
    @PostMapping("/get/{code}")
    public ReturnModel<SysDict> get(@PathVariable("code") String code) {
        return ReturnModel.ok(sysDictService.getByCode(code));
    }

    /**
     * 新增字典
     *
     * @author cqmike
     * @param param
     * @since 2021/6/4 14:14
     * @return : com.space.cornerstone.framework.core.domain.model.ReturnModel<java.lang.Void>
     */
    @Log(name = "新增字典", type = OperationLogType.ADD)
    @PostMapping("/save")
    public ReturnModel<Void> saveUser(@Validated(Add.class) @RequestBody SysDict param) {
        sysDictService.saveOrUpdateDict(param);
        return ReturnModel.ok();
    }

    /**
     * 编辑字典
     *
     * @author cqmike
     * @param param
     * @since 2021/6/4 14:15
     * @return
     */
    @Log(name = "编辑字典", type = OperationLogType.UPDATE)
    @PutMapping("/update")
    public ReturnModel<Void> updateUser(@Validated(Update.class) @RequestBody SysDict param) {
        sysDictService.saveOrUpdateDict(param);
        return ReturnModel.ok();
    }

    /**
     * 删除字典
     *
     * @author cqmike
     * @param code
     * @since 1.0.0
     * @return
     */
    @Log(name = "删除字典", type = OperationLogType.DELETE)
    @DeleteMapping("/delete/{code}")
    public ReturnModel<Void> delete(@PathVariable("code") String code) {
        sysDictService.deleteByCode(code);
        return ReturnModel.ok();
    }
}
