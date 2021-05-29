package com.space.cornerstone.system.controller;

import cn.hutool.core.util.StrUtil;
import com.space.cornerstone.framework.core.controller.BaseController;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import com.space.cornerstone.framework.core.util.PreconditionsUtil;
import com.space.cornerstone.system.domain.param.LoginParam;
import com.space.cornerstone.system.domain.vo.UserVo;
import com.space.cornerstone.system.service.LoginService;
import com.space.cornerstone.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName LoginController.java
 * @Description 登录
 * @createTime 2021年05月25日 08:11:00
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController extends BaseController {

    private final LoginService loginService;
    private final SysUserService sysUserService;

    /**
     * @Description 登录接口
     * @author chen qi
     * @param loginParam
     * @since 2021/5/27 12:48
     * @return : com.space.cornerstone.framework.core.domain.model.ReturnModel<java.lang.String>
     */
    @PostMapping("/login")
    public ReturnModel<String> login(@RequestBody LoginParam loginParam) {

        PreconditionsUtil.checkArgument(StrUtil.isNotEmpty(loginParam.getS()) && StrUtil.isNotEmpty(loginParam.getD()), "用户名或密码不能为空");
        final String login = loginService.login(loginParam.getS(), loginParam.getD(), loginParam.getC(), loginParam.getU());
        return ReturnModel.ok(login);
    }


    @GetMapping("/getAuthUserInfo")
    public ReturnModel<UserVo> getAuthUserInfo(Long userId) {
        return ReturnModel.ok(sysUserService.findAuthInfoByUserId(userId));
    }



}
