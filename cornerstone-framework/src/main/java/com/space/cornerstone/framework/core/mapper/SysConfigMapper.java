package com.space.cornerstone.framework.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.space.cornerstone.framework.core.domain.entity.system.SysConfig;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfig> {
}