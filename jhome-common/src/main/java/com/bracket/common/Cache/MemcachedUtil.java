package com.bracket.common.Cache;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MemcachedUtil {

	private String poolName;// 缓存池名称
	private List<MemCachedClient> memCachedClients = new ArrayList<MemCachedClient>();// 换成客户端
	private List<SockIOPool> sockIOPools = new ArrayList<SockIOPool>();// IO池

	/**
	 * 关闭IO池
	 */
	public void shutdown() {
		for (SockIOPool sockIOPool : sockIOPools) {
			sockIOPool.shutDown();
		}
	}

	/**
	 * 初始化缓冲池
	 */
	public void InitMemCacheMagent(String pooName) {
		this.poolName = pooName;
		if (this.poolName.isEmpty()||pooName=="") {
			this.poolName = "ApiCache";
		}
		String[] IpHost = {"10.1.23.13"};
		for (int i = 0; i < IpHost.length; i++) {
			this.poolName = this.poolName + (i + 1);
			String serverIp = IpHost[i];
			String[] servers = { serverIp + ":11211" };

			// 初始化
			SockIOPool pool = SockIOPool.getInstance(this.poolName);
			pool.setServers(servers); 
		    //设置初始连接数
	        pool.setInitConn(5);
	        //设置最小连接数
	        pool.setMinConn(5);
	        //设置最大连接数
	        pool.setMaxConn(200);
	        //设置可用连接的最长等待时间
	        pool.setMaxIdle(1000*30*30);
	        //设置连接池维护线程的睡眠时间，设置为0，维护线程不启动
	        pool.setMaintSleep(30);
	        //设置Nagle算法，设置为false，因为通讯数据量比较大要求相应及时
	        pool.setNagle(false);
	        //设置socket读取等待超时时间
	        pool.setSocketTO(30);
	        //设置连接等待超时值
	        pool.setSocketConnectTO(0);
	        // 故障转移
	        pool.setFailover(true); 
	        //设置完参数后，启动pool
	        pool.initialize(); 
			sockIOPools.add(pool);
			MemCachedClient memCached = new MemCachedClient(this.poolName);
			memCachedClients.add(memCached);
		}

	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @param expiry
	 * @return
	 */
	public boolean Set(String key, Object value, Date expiry) {
		boolean isOk = false;
		// 循环加入每个缓存池
		for (MemCachedClient mc : memCachedClients) {
			try {
				if (mc.set(key, value, expiry)) {
					// 只要有一个成功，就表示true
					isOk = true;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println(e.getMessage());
			} 
		}
		return isOk;
	}

	/**
	 * 从缓存中获取数据
	 * 
	 * @param key
	 * @return
	 */
	public Object Get(String key) {
		Object o = null;
		Calendar calendar = Calendar.getInstance();
		// 当前时间秒为偶数时,从第一个取
		if (calendar.get(Calendar.SECOND) % 2 == 0) {
			// 循环从每个缓存池中取数据，只到取到数据为止

			for (int i = 0; i < memCachedClients.size(); i++) {
				o = (MemCachedClient) memCachedClients.get(i).get(key);
				if (o != null) {
					// 只要取到值，就退出循环
					break;
				}
			}
		} else {
			// 循环从每个缓存池中取数据，只到取到数据为止
			for (int i = memCachedClients.size() - 1; i >= 0; i--) {
				o = memCachedClients.get(i).get(key);
				if (o != null) {
					// 只要取到值，就退出循环
					break;
				}
			}
		}
		return o;
	}

	/**
	 * 从缓存池中删除数据
	 * 
	 * @param key
	 * @return
	 */
	public boolean Delete(String key) {
		boolean isOk = true;
		// 循环从每个缓存池中删除数据
		for (MemCachedClient mc : memCachedClients) {
			isOk = mc.delete(key);
		}
		return isOk;
	}

}
