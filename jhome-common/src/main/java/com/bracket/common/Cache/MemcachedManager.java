package com.bracket.common.Cache;

public class MemcachedManager {
	public MemcachedManager() {
	}
    public final static MemcachedUtil memCache = new MemcachedUtil();

    public static void InitMemCache(String poolName) {
        if (memCache != null) {
            System.out.println("初始化缓存");
            memCache.InitMemCacheMagent(poolName);
        } else {
            System.err.println("已经初始化");
        }
    }

    public static MemcachedUtil getMemCache() {
        return memCache;
    }

}
