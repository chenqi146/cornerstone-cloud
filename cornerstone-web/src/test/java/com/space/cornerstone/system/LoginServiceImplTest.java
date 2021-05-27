package com.space.cornerstone.system;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.space.cornerstone.framework.core.util.JacksonUtil;
import com.space.cornerstone.system.domain.entity.SysUser;
import com.space.cornerstone.system.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceImplTest {

    @Autowired
    private SysUserService SysUserService;

    @Test
    public void test() {
        final LambdaUpdateWrapper<SysUser> lambda = new UpdateWrapper<SysUser>().lambda().eq(SysUser::getUserName, "qqq").set(SysUser::getEmail, "aaa");
        SysUserService.update(lambda);
        System.out.println(JacksonUtil.toJson(lambda));
    }
}