package com.space.cornerstone.system.controller;

import com.space.cornerstone.framework.core.controller.BaseController;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import com.space.cornerstone.system.domain.param.LoginParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName LoginController.java
 * @Description LoginController
 * @createTime 2021年05月25日 08:11:00
 */
@RestController
@RequiredArgsConstructor
public class LoginController extends BaseController {

    @PostMapping("/login")
    public ReturnModel<String> login(@RequestBody LoginParam loginParam) {


        return ReturnModel.ok();
    }



}
