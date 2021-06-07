package com.space.cornerstone.framework.core.service;

import com.space.cornerstone.framework.core.domain.entity.system.SysConfig;
import com.space.cornerstone.framework.core.domain.entity.system.SysDict;
import com.space.cornerstone.framework.core.domain.model.Paging;
import com.space.cornerstone.framework.core.domain.param.system.SysConfigParam;
import com.space.cornerstone.framework.core.domain.param.system.SysDictParam;
import com.space.cornerstone.framework.core.service.BaseService;
import org.apache.catalina.User;

/**
 * @author chen qi
 * @version 1.0.0
 * @InterfaceName SysConfigService.java
 * @Description SysConfigService
 * @createTime 2021年05月23日 09:29:00
 */
public interface SysConfigService extends BaseService<SysConfig> {

    /**
     * 根据code查询详情
     *
     * @author cqmike
     * @param code
     * @since 1.0.0
     * @return
     */
    SysConfig getByCode(String code);


    /**
     * 获取分页列表
     *
     * @author cqmike
     * @param sysConfigParam
     * @since 1.0.0
     * @return
     */
    Paging<SysConfig> listPage(SysConfigParam sysConfigParam);
}
