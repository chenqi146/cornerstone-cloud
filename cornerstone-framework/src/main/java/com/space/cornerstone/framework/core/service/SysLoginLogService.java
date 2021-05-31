package com.space.cornerstone.framework.core.service;

import com.space.cornerstone.framework.core.domain.entity.system.SysLoginLog;

/**
 * @author chen qi
 * @version 1.0.0
 * @InterfaceName SysLoginLogService.java
 * @Description SysLoginLogService
 * @createTime 2021年05月31日 23:25:00
 */
public interface SysLoginLogService extends BaseService<SysLoginLog> {

    /**
     * 异步存储登录日志
     * @author cqmike
     * @param log
     * @since 2021-05-31 23:33
     */
    void asyncSaveLog(SysLoginLog log);
}
