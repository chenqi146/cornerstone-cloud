package com.space.cornerstone.web;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.space.cornerstone.framework.core.util.JacksonUtil;
import com.space.cornerstone.system.domain.entity.SysUser;
import com.space.cornerstone.system.service.SysUserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@MapperScan(basePackages = "com.space.cornerstone.**.mapper")
@SpringBootApplication(scanBasePackages = {"com.space.cornerstone"})
public class CornerstoneApplication {


    public static void main(String[] args) {
        SpringApplication.run(CornerstoneApplication.class, args);
    }

    @Resource
    private SysUserService sysUserService;

    @GetMapping("/profile/sss")
    public void test() {
        final LambdaUpdateWrapper<SysUser> lambda = new UpdateWrapper<SysUser>().lambda().eq(SysUser::getUserName, "qqq").set(SysUser::getEmail, "aaa");
        sysUserService.update(lambda);
        System.out.println(JacksonUtil.toJson(lambda));
    }
}
