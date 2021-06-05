package com.space.cornerstone.framework.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.space.cornerstone.framework.core.domain.entity.system.SysDict;
import com.space.cornerstone.framework.core.domain.entity.system.SysDictData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {

    SysDict getByCode(String typeCode);
}