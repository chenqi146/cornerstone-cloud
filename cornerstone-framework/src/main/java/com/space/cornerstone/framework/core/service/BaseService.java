package com.space.cornerstone.framework.core.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.space.cornerstone.framework.core.util.LambdaColumnUtil;

/**
 * @author chen qi
 * @version 1.0.0
 * @InterfaceName BaseService.java
 * @Description BaseService
 * @createTime 2021年05月18日 23:33:00
 */
public interface BaseService<T> extends IService<T> {


    /**
     * 获取对应字段的数据表列名称
     *
     * @param func
     * @return
     */
    default String getLambdaColumn(SFunction<T, ?> func) {
        return new LambdaColumnUtil<T>().get(func);
    }
}
