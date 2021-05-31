package com.space.cornerstone.framework.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.domain.entity.system.SysLoginLog;
import com.space.cornerstone.framework.core.domain.entity.system.SysOperationLog;
import com.space.cornerstone.framework.core.mapper.SysLoginLogMapper;
import com.space.cornerstone.framework.core.mapper.SysOperationLogMapper;
import com.space.cornerstone.framework.core.service.SysLoginLogService;
import com.space.cornerstone.framework.core.service.SysOperationLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName SysOperationLogServiceImpl.java
 * @Description SysOperationLogServiceImpl
 * @createTime 2021年05月31日 23:26:00
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLog> implements SysOperationLogService {

    /**
     * 异步存储日志
     *
     * @param operationLog
     * @author cqmike
     * @since 2021-05-31 23:33
     */
    @Async(Constant.ASYNC_EXECUTOR)
    @Override
    public void asyncSaveLog(SysOperationLog operationLog) {
        try {
            this.save(operationLog);
        } catch (Exception e) {
            log.error("异步存储操作日志异常, data: {}, error: ", operationLog, e);
        }
    }
}
