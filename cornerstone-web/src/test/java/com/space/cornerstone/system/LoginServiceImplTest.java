package com.space.cornerstone.system;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.space.cornerstone.framework.core.util.JacksonUtil;
import com.space.cornerstone.system.domain.entity.SysUser;
import com.space.cornerstone.system.service.SysUserService;
import com.space.cornerstone.web.CornerstoneApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CornerstoneApplication.class)
public class LoginServiceImplTest {

    @Resource
    private SysUserService sysUserService;

    @Test
    public void test() {
        final LambdaUpdateWrapper<SysUser> lambda = new UpdateWrapper<SysUser>().lambda().eq(SysUser::getUserName, "qqq").set(SysUser::getEmail, "qqq111das");
//        sysUserService.update(lambda);
//        System.out.println(JacksonUtil.toJson(lambda));

        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        sysUser.setPassword("ssss");
        sysUser.setVersion(0);
        sysUserService.updateById(sysUser);
        System.out.println(JacksonUtil.toJson(sysUser));

    }
}