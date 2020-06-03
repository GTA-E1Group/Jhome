package com.daxu.common.RedissonHandler;

import org.redisson.api.RedissonClient;

public interface LockHandler {
    void Invoker(RedissonClient redissonClient);
}
