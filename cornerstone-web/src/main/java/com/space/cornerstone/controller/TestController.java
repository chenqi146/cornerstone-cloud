package com.space.cornerstone.controller;

import com.space.cornerstone.framework.core.contoller.BaseController;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 大类
 * @author chen qi
 * @version 1.0.0
 * @ClassName TestController.java
 * @Description TODO
 * @createTime 2021年05月20日 08:39:00
 */
@RestController
public class TestController extends BaseController {

    /**
     * 测试
     * @Description ddd
     * @author chen qi
     * @param name 名称
     * @since  2021-05-20 8:42
     * @return : com.space.cornerstone.framework.core.domain.model.ReturnModel<java.lang.String>
     */
    @GetMapping("test")
    public ReturnModel<String> testModel(String name) {

        return ReturnModel.ok();
    }
}
