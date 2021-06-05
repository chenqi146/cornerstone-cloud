package com.space.cornerstone.framework.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.cornerstone.framework.core.domain.entity.system.SysDictData;
import com.space.cornerstone.framework.core.mapper.SysDictDataMapper;
import com.space.cornerstone.framework.core.service.SysDictDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService {

}
