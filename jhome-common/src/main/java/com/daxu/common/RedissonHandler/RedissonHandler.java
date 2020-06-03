package com.daxu.common.RedissonHandler;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class RedissonHandler {
    private String lockName = "";//锁名称
    private String redisUrl = "";//缓存地址
    private RedissonClient redissonClient;

    public RedissonHandler(String lockName, String revisionUrl) {
        this.lockName = lockName;
        this.redisUrl = revisionUrl;
        org.redisson.config.Config config = new org.redisson.config.Config();
        config.useSingleServer().setAddress(this.redisUrl).setDatabase(0);
        //添加主从配置
        //config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});
        this.redissonClient = Redisson.create(config);
    }

    /**
     * 添加分布式锁
     */
    public void AddLokeHandler(LockHandler lockHandler) {
        RLock lock = redissonClient.getLock(this.lockName);
        try {
            lockHandler.Invoker(this.redissonClient);
        } catch (Exception ex) {

        } finally {
            lock.unlock();
        }
    }

    /**
     * BloomFilter 过滤器
     */
    public void GetBloomFilter() {
        redissonClient.getBloomFilter("");
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    public String getLockName() {
        return lockName;
    }

    public String getRedisUrl() {
        return redisUrl;
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public void setRedisUrl(String redisUrl) {
        this.redisUrl = redisUrl;
    }

}
