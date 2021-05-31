package com.space.cornerstone.system.controller;

import com.space.cornerstone.framework.core.controller.BaseController;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import com.space.cornerstone.system.domain.param.SysUserParam;
import com.space.cornerstone.system.domain.vo.SysUserQueryVo;
import com.space.cornerstone.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户接口
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
     * @author chen qi
     * @param sysUserParam sysUserParam
     * @since 2021/5/31 15:46
     * @return : com.space.cornerstone.framework.core.domain.model.ReturnModel<com.space.cornerstone.system.domain.vo.SysUserQueryVo>
     */
    @GetMapping("/list")
    public ReturnModel<SysUserQueryVo> list(SysUserParam sysUserParam) {


        return ReturnModel.ok();
    }
}
