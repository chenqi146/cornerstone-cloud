package com.space.cornerstone.framework.core.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.domain.entity.system.SysDict;
import com.space.cornerstone.framework.core.domain.entity.system.SysDictData;
import com.space.cornerstone.framework.core.domain.model.PageInfo;
import com.space.cornerstone.framework.core.domain.model.Paging;
import com.space.cornerstone.framework.core.domain.param.system.SysDictParam;
import com.space.cornerstone.framework.core.redis.RedisClient;
import com.space.cornerstone.framework.core.service.SysDictDataService;
import com.space.cornerstone.framework.core.service.SysDictService;
import com.space.cornerstone.framework.core.mapper.SysDictMapper;
import com.space.cornerstone.framework.core.util.PreconditionsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 编辑完成之后需要刷新缓存
 * @author chen qi
 * @version 1.0.0
 * @ClassName SysDictServiceImpl.java
 * @Description SysDictServiceImpl
 * @createTime 2021年05月25日 22:01:00
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SysDictServiceImpl extends BaseServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    private final SysDictDataService sysDictDataService;
    private final RedisClient redisClient;

    /**
     * 分页字典类型列表
     *
     * @param sysDictParam
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    @Override
    public Paging<SysDict> listPage(SysDictParam sysDictParam) {

        Page<SysDict> page = new PageInfo<>(sysDictParam);
        LambdaQueryWrapper<SysDict> wrapper = Wrappers.lambdaQuery(new SysDict().setCode(sysDictParam.getCode()))
                .lt(sysDictParam.getEndTime() != null, SysDict::getCreateTime, sysDictParam.getEndTime())
                .gt(sysDictParam.getBeginTime() != null, SysDict::getCreateTime, sysDictParam.getBeginTime());

        IPage<SysDict> dictPage = this.page(page, wrapper);
        return Paging.<SysDict>builder().build(dictPage);
    }

    /**
     * 根据code获取类型字典
     *
     * @param typeCode
     * @author cqmike
     * @since 1.0.0
     * @return
     */
    @Override
    public SysDict getByCode(String typeCode) {
        PreconditionsUtil.checkArgument(StrUtil.isNotEmpty(typeCode), "字典参数不能为空");
        String key = Constant.DICT + typeCode;
        SysDict sysDict = redisClient.get(key, SysDict.class);
        return Optional.ofNullable(sysDict).orElseGet(() -> {
            SysDict dbDict = getBaseMapper().getByCode(typeCode);
            redisClient.set(key, dbDict);
            return dbDict;
        });
    }


    /**
     * 更新或新增字典
     *
     * @param sysDict
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    @Override
    public void saveOrUpdateDict(SysDict sysDict) {

        List<SysDictData> dataList = sysDict.getDataList();
        if (CollUtil.isEmpty(dataList)) {
            return;
        }

        SysDict dict = this.getByCode(sysDict.getCode());

        if (dict == null) {
            this.save(sysDict);
        } else {
            this.update(Wrappers.lambdaUpdate(sysDict.setId(dict.getId())));
        }

        sysDictDataService.remove(Wrappers.lambdaUpdate(SysDictData.class).eq(SysDictData::getTypeCode, sysDict.getCode()));


        dataList.forEach(d -> d.setTypeCode(sysDict.getCode()));

        sysDictDataService.saveBatch(dataList);
        dict = this.getByCode(sysDict.getCode());
        String key = Constant.DICT + sysDict.getCode();
        redisClient.set(key, dict);
    }

    /**
     * 通过字典类型code删除
     *
     * @param code
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    @Override
    public void deleteByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return;
        }

        this.remove(Wrappers.lambdaUpdate(SysDict.class).eq(SysDict::getCode, code));
        sysDictDataService.remove(Wrappers.lambdaUpdate(SysDictData.class).eq(SysDictData::getTypeCode, code));
        redisClient.del(Constant.DICT + code);
    }

}
