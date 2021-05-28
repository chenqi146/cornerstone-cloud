package com.space.cornerstone.framework.core.redis;

import com.space.cornerstone.framework.core.util.RedisLockUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 锁的延长时间守护线程
 *
 * @author chen qi
 * @date 2020-10-29 18:50
 **/
@Slf4j
public class LockExpandDaemonRunnable implements Runnable {

    private final RedisClient redisClient;
    private final String key;
    private final String value;
    private final int lockTime;

    public LockExpandDaemonRunnable(RedisClient redisClient, String key, String value, int lockTime) {
        this.redisClient = redisClient;
        this.key = key;
        this.value = value;
        this.lockTime = lockTime;
        this.signal = Boolean.TRUE;
    }

    private volatile Boolean signal;

    public void stop() {
        this.signal = Boolean.FALSE;
    }

    @Override
    public void run() {

        // 先等待锁的过期时间的三分之二  如果还持有锁 进行一次续期 重复
        int waitTime = lockTime * 1000 * 2 / 3;
        while (signal) {
            try {
                Thread.sleep(waitTime);
                if (RedisLockUtil.expandLockTime(redisClient, key, value, lockTime)) {
                    // 延长过期时间成功
                    log.debug("延长过期时间成功，本次等待{}ms，将重置key为{}的锁超时时间重置为{}s", waitTime, key, lockTime);
                } else {
                    log.debug("延长过期时间失败, 此线程LockExpandDaemonRunnable中断");
                    this.stop();
                }
            } catch (InterruptedException e) {
                log.debug("此线程LockExpandDaemonRunnable被强制中断", e);
            } catch (Exception e) {
                log.error("锁的延长时间守护线程发送异常", e);
            }
        }

    }
}
