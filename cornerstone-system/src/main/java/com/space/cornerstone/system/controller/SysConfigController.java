package com.space.cornerstone.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.space.cornerstone.framework.core.annotation.Log;
import com.space.cornerstone.framework.core.controller.BaseController;
import com.space.cornerstone.framework.core.domain.entity.system.SysConfig;
import com.space.cornerstone.framework.core.domain.model.Paging;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import com.space.cornerstone.framework.core.domain.param.system.SysConfigParam;
import com.space.cornerstone.framework.core.enums.OperationLogType;
import com.space.cornerstone.framework.core.redis.RedisClient;
import com.space.cornerstone.framework.core.service.SysConfigService;
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
@RequestMapping("/config")
public class SysConfigController extends BaseController {

    private final SysConfigService sysConfigService;
    private final RedisClient redisClient;


    /**
     * 配置列表查询
     * @apiNote 搜索时列表结构
     * @author cqmike
     * @since 2021/6/4 14:14
     * @return
     */
    @Log(name = "配置列表查询", type = OperationLogType.LIST)
    @GetMapping("/list")
    public ReturnModel<Paging<SysConfig>> list(SysConfigParam param) {

        final Paging<SysConfig> list = sysConfigService.listPage(param);
        return ReturnModel.ok(list);
    }

    /**
     * 新增配置
     *
     * @author cqmike
     * @param code
     * @since 2021/6/4 14:14
     * @return
     */
    @Log(name = "查询配置详情", type = OperationLogType.QUERY)
    @PostMapping("/get/{code}")
    public ReturnModel<SysConfig> get(@PathVariable("code") String code) {
        return ReturnModel.ok(sysConfigService.getByCode(code));
    }

    /**
     * 新增配置
     *
     * @author cqmike
     * @param param
     * @since 2021/6/4 14:14
     * @return : com.space.cornerstone.framework.core.domain.model.ReturnModel<java.lang.Void>
     */
    @Log(name = "新增配置", type = OperationLogType.ADD)
    @PostMapping("/save")
    public ReturnModel<Void> saveUser(@Validated(Add.class) @RequestBody SysConfig param) {
        sysConfigService.save(param);
        return ReturnModel.ok();
    }

    /**
     * 编辑配置
     *
     * @author cqmike
     * @param param
     * @since 2021/6/4 14:15
     * @return
     */
    @Log(name = "编辑配置", type = OperationLogType.UPDATE)
    @PutMapping("/update")
    public ReturnModel<Void> updateUser(@Validated(Update.class) @RequestBody SysConfig param) {
        sysConfigService.updateById(param);
        return ReturnModel.ok();
    }

    /**
     * 删除配置
     *
     * @author cqmike
     * @param code
     * @since 1.0.0
     * @return
     */
    @Log(name = "删除配置", type = OperationLogType.DELETE)
    @DeleteMapping("/delete/{code}")
    public ReturnModel<Void> delete(@PathVariable("code") String code) {
        sysConfigService.remove(Wrappers.lambdaUpdate(SysConfig.class).eq(SysConfig::getCode, code));
        return ReturnModel.ok();
    }
}
