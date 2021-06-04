package com.space.cornerstone.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.cornerstone.framework.core.service.impl.BaseServiceImpl;
import com.space.cornerstone.system.domain.entity.SysDept;
import com.space.cornerstone.system.domain.vo.SysDeptTreeVo;
import com.space.cornerstone.system.mapper.SysDeptMapper;
import com.space.cornerstone.system.service.SysDeptService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName SysDeptServiceImpl.java
 * @Description TODO
 * @createTime 2021年05月25日 22:01:00
 */
@Service
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    /**
     * 获取部门树
     *
     * @return : java.util.Set<com.space.cornerstone.system.domain.vo.SysDeptTreeVo>
     * @author cqmike
     * @since 2021/6/3 11:34
     */
    @Override
    public Set<SysDeptTreeVo> getDeptTree() {
        return null;
    }
}
