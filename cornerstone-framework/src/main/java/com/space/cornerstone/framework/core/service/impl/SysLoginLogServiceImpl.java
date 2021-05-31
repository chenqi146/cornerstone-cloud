package com.space.cornerstone.framework.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.domain.entity.system.SysLoginLog;
import com.space.cornerstone.framework.core.mapper.SysLoginLogMapper;
import com.space.cornerstone.framework.core.service.SysLoginLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName SysLoginLogServiceImpl.java
 * @Description SysLoginLogServiceImpl
 * @createTime 2021年05月31日 23:26:00
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {

    /**
     * 异步存储登录日志
     *
     * @param loginLog
     * @author cqmike
     * @since 2021-05-31 23:33
     */
    @Async(Constant.ASYNC_EXECUTOR)
    @Override
    public void asyncSaveLog(SysLoginLog loginLog) {
        try {
            this.save(loginLog);
        } catch (Exception e) {
            log.error("异步存储登录日志异常, data: {}, error: ", loginLog, e);
        }
    }
}
