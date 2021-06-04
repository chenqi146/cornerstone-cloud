package com.space.cornerstone.web.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.space.cornerstone.framework.core.controller.BaseController;
import com.space.cornerstone.framework.core.domain.model.PageInfo;
import com.space.cornerstone.framework.core.domain.model.Paging;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import com.space.cornerstone.system.domain.entity.SysUser;
import com.space.cornerstone.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 大类
 * @author chen qi
 * @version 1.0.0
 * @ClassName TestController.java
 * @Description TODO
 * @createTime 2021年05月20日 08:39:00
 */
@RestController
@RequiredArgsConstructor
public class TestController extends BaseController {

    private final SysUserService sysUserService;

    /**
     * 测试
     * @Description ddd
     * @author chen qi
     * @param name 名称
     * @return : com.space.cornerstone.framework.core.domain.model.Paging<com.space.cornerstone.framework.core.domain.entity.BaseEntity>
     */
    @GetMapping("test")
    public ReturnModel<Paging<SysUser>> testModel(String name) {

        final SysUser sysUser = new SysUser();
        sysUser.setUserName(name);
        final PageInfo<SysUser> page = sysUserService.page(new PageInfo<>(), Wrappers.lambdaQuery(sysUser).gt(SysUser::getUpdateTime, LocalDateTime.now()));

        return ReturnModel.ok(Paging.builder().build(page));
    }
}
