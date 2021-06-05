package com.space.cornerstone.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.exception.BusinessException;
import com.space.cornerstone.framework.core.service.impl.BaseServiceImpl;
import com.space.cornerstone.system.domain.entity.SysDept;
import com.space.cornerstone.system.domain.entity.SysMenu;
import com.space.cornerstone.system.domain.vo.SysDeptTreeVo;
import com.space.cornerstone.system.mapper.SysDeptMapper;
import com.space.cornerstone.system.service.SysDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName SysDeptServiceImpl.java
 * @Description SysDeptServiceImpl
 * @createTime 2021年05月25日 22:01:00
 */
@Service
@Transactional(rollbackFor = Exception.class)
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

        List<SysDept> deptList = this.list();

        Map<Long, SysDeptTreeVo> convertMap = deptList.stream()
                .collect(Collectors.toMap(SysDept::getId, SysDeptTreeVo::convert, (k1, k2) -> k1));

        for (SysDept dept : deptList) {
            final SysDeptTreeVo parent = convertMap.get(dept.getParentId());
            if (parent == null) {
                continue;
            }
            final SysDeptTreeVo treeVo = SysDeptTreeVo.convert(dept);
            parent.getChildren().add(treeVo);
        }

        return convertMap.values().stream().filter(Objects::nonNull)
                .filter(m -> Objects.equals(m.getParentId(), Constant.ROOT_ID)).collect(Collectors.toSet());
    }

    /**
     * 新增部门
     *
     * @param sysDept
     * @author cqmike
     * @since 2021/6/3 11:44
     */
    @Override
    public void saveDept(SysDept sysDept) {
        this.checkDept(sysDept);
        this.save(sysDept);
    }

    /**
     * 编辑部门
     *
     * @param sysDept
     * @author cqmike
     * @since 2021/6/3 11:44
     */
    @Override
    public void updateDept(SysDept sysDept) {
        this.checkDept(sysDept);
        this.updateById(sysDept);
    }


    /**
     * 校验部门参数
     *
     * @author cqmike
     * @param sysDept
     * @since 2021/6/3 13:01
     */
    private void checkDept(SysDept sysDept) {
        final Long parentId = sysDept.getParentId();
        if (parentId != null && parentId > 0) {
            Optional.ofNullable(this.getById(parentId)).orElseThrow(() -> new BusinessException("此父级部门已经被删除"));
        } else {
            sysDept.setParentId(Constant.ROOT_ID);
        }
    }

    /**
     * 根据id删除部门及其子部门
     *
     * @param id
     * @author cqmike
     * @since 2021/6/3 18:28
     */
    @Override
    public void deleteById(Long id) {

        if (id == null) {
            return;
        }

        final SysDept sysMenu = this.getById(id);
        if (sysMenu == null) {
            return;
        }

        final List<SysDept> menuList = this.list();
        Map<Long, List<SysDept>> menuMap = menuList.stream().collect(Collectors.groupingBy(SysDept::getParentId));

        Set<Long> allIdSet = CollUtil.newHashSet(id);
        collectTreeIdList(allIdSet, menuMap, id);

        this.removeByIds(allIdSet);
    }



    /**
     * 获取当前节点的所有子节点id
     *
     * @author cqmike
     * @param allIdSet
     * @param deptMap
     * @param currentId
     * @since 1.0.0
     * @return
     */
    private void collectTreeIdList(Set<Long> allIdSet, Map<Long, List<SysDept>> deptMap, Long currentId) {

        if (CollUtil.isEmpty(deptMap)) {
            return;
        }

        List<SysDept> children = deptMap.get(currentId);
        if (CollUtil.isEmpty(children)) {
            return;
        }

        for (SysDept child : children) {
            allIdSet.add(child.getId());
            collectTreeIdList(allIdSet, deptMap, child.getId());
        }
    }
}
