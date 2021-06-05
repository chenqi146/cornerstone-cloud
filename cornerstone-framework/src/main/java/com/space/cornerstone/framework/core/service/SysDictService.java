package com.space.cornerstone.framework.core.service;

import com.space.cornerstone.framework.core.domain.entity.system.SysDict;
import com.space.cornerstone.framework.core.domain.model.Paging;
import com.space.cornerstone.framework.core.domain.param.system.SysDictParam;

/**
 * @author chen qi
 * @version 1.0.0
 * @InterfaceName SysDictService.java
 * @Description SysDictService
 * @createTime 2021年05月23日 09:29:00
 */
public interface SysDictService extends BaseService<SysDict> {

    /**
     * 分页字典类型列表
     *
     * @author cqmike
     * @param sysDictParam
     * @since 1.0.0
     * @return
     */
    Paging<SysDict> listPage(SysDictParam sysDictParam);

    /**
     * 根据code获取类型字典
     *
     * @author cqmike
     * @param typeCode
     * @since 1.0.0
     * @return
     */
    SysDict getByCode(String typeCode);

    /**
     * 更新或新增字典
     *
     * @author cqmike
     * @param sysDict
     * @since 1.0.0
     * @return
     */
    void saveOrUpdateDict(SysDict sysDict);

    /**
     * 通过字典类型code删除
     *
     * @author cqmike
     * @param code
     * @since 1.0.0
     * @return
     */
    void deleteByCode(String code);

}
