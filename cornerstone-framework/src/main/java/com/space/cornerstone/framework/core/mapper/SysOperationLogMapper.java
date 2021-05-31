package com.space.cornerstone.framework.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.space.cornerstone.framework.core.domain.entity.system.SysDict;
import com.space.cornerstone.framework.core.domain.entity.system.SysOperationLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysOperationLogMapper extends BaseMapper<SysOperationLog> {
}