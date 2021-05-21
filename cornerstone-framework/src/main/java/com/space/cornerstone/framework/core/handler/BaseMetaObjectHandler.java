package com.space.cornerstone.framework.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.space.cornerstone.framework.core.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName BaseMetaObjectHandler.java
 * @Description  基础数据拦截器
 * @createTime 2021年05月21日 23:31:00
 */
@Slf4j
@Component
public class BaseMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 代码设置时间  防止 程序服务器和数据库服务器时间不一致
        this.fillStrategy(metaObject, Constant.CREATE_TIME, LocalDateTime.now());
        this.fillStrategy(metaObject, Constant.UPDATE_TIME, LocalDateTime.now());

        // TODO: 2021-05-21 设置线程变量里面的用户信息
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, Constant.UPDATE_TIME, LocalDateTime.now());
    }
}
