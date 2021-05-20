package com.space.cornerstone.framework.core.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.cornerstone.framework.core.service.BaseService;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName BaseServiceImpl.java
 * @Description BaseServiceImpl
 * @createTime 2021年05月20日 21:04:00
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M, T> implements BaseService<T> {
}
