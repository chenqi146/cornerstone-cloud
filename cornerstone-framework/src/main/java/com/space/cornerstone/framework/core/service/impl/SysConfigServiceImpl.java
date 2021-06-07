package com.space.cornerstone.framework.core.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.domain.entity.system.SysConfig;
import com.space.cornerstone.framework.core.domain.entity.system.SysConfig;
import com.space.cornerstone.framework.core.domain.model.PageInfo;
import com.space.cornerstone.framework.core.domain.model.Paging;
import com.space.cornerstone.framework.core.domain.param.system.SysConfigParam;
import com.space.cornerstone.framework.core.mapper.SysConfigMapper;
import com.space.cornerstone.framework.core.redis.RedisClient;
import com.space.cornerstone.framework.core.service.BaseService;
import com.space.cornerstone.framework.core.service.SysConfigService;
import com.space.cornerstone.framework.core.util.PreconditionsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author chen qi
 * @version 1.0.0
 * @InterfaceName SysConfigService.java
 * @Description SysConfigService
 * @createTime 2021年05月23日 09:29:00
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {

    private final RedisClient redisClient;

    /**
     * 根据code查询详情
     *
     * @param code
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    @Override
    public SysConfig getByCode(String code) {
        PreconditionsUtil.checkArgument(StrUtil.isNotEmpty(code), "code参数不能为空");

        String key = Constant.CONFIG + code;
        SysConfig sysConfig = redisClient.get(key, SysConfig.class);
        return Optional.ofNullable(sysConfig).orElseGet(() -> {
            SysConfig dbConfig = lambdaQuery().eq(SysConfig::getCode, code).one();
            redisClient.set(key, dbConfig);
            return dbConfig;
        });
    }

    /**
     * 获取分页列表
     *
     * @param sysConfigParam
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    @Override
    public Paging<SysConfig> listPage(SysConfigParam sysConfigParam) {

        Page<SysConfig> page = new PageInfo<>(sysConfigParam);
        LambdaQueryWrapper<SysConfig> wrapper = Wrappers.lambdaQuery(new SysConfig().setCode(sysConfigParam.getCode()))
                .lt(sysConfigParam.getEndTime() != null, SysConfig::getCreateTime, sysConfigParam.getEndTime())
                .gt(sysConfigParam.getBeginTime() != null, SysConfig::getCreateTime, sysConfigParam.getBeginTime());

        IPage<SysConfig> dictPage = this.page(page, wrapper);
        return Paging.<SysConfig>builder().build(dictPage);
    }

    /**
     * 插入一条记录（选择字段，策略插入）
     *
     * @param entity 实体对象
     */
    @Override
    public boolean save(SysConfig entity) {
        boolean save = super.save(entity);
        if (save) {
            redisClient.set(Constant.CONFIG + entity.getCode(), entity);
        }
        return save;
    }

    /**
     * 根据 entity 条件，删除记录
     *
     * @param queryWrapper 实体包装类 {@link QueryWrapper}
     */
    @Override
    public boolean remove(Wrapper<SysConfig> queryWrapper) {
        boolean remove = super.remove(queryWrapper);
        if (remove && StrUtil.isNotEmpty(queryWrapper.getEntity().getCode())) {
            redisClient.del(Constant.CONFIG + queryWrapper.getEntity().getCode());
        }
        return remove;
    }

    /**
     * 根据 ID 选择修改
     *
     * @param entity 实体对象
     */
    @Override
    public boolean updateById(SysConfig entity) {
        boolean update = super.updateById(entity);
        if (update) {
            SysConfig config = this.getById(entity.getId());
            redisClient.set(Constant.CONFIG + entity.getCode(), config);
        }
        return update;
    }
}
