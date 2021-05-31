package com.space.cornerstone.framework.core.service;

import com.space.cornerstone.framework.core.domain.entity.system.SysLoginLog;
import com.space.cornerstone.framework.core.domain.entity.system.SysOperationLog;

/**
 * @author chen qi
 * @version 1.0.0
 * @InterfaceName SysOperationLogService.java
 * @Description SysOperationLogService
 * @createTime 2021年05月31日 23:25:00
 */
public interface SysOperationLogService extends BaseService<SysOperationLog> {


    /**
     * 异步存储日志
     * @author cqmike
     * @param log
     * @since 2021-05-31 23:33
     */
    void asyncSaveLog(SysOperationLog log);
}
