package com.Spider.clientCenter;

import com.bracket.common.Http.HttpClient;
import com.bracket.common.Http.HttpDao;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理进程
 * @author Administrator
 *
 */
public class threadProcess2 implements Runnable {
    private String pushInfoString;
	public threadProcess2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public threadProcess2(String pushInfoString) {
		super();
		this.pushInfoString=pushInfoString;
	} 
	/*
	 * 执行
	 */
	public void run() {
		System.out.println(String.format("进程中收到消息%s", pushInfoString));
		System.out.println(" ");
		System.out.println("创建爬虫");
		System.out.println("爬取数据");
		System.out.println("开始提交爬虫爬取的数据");  
		try {
			//请求服务器返回爬虫爬取结果
			Map<String, Object> data=new HashMap<String, Object>();
			data.put("name", new String("daxu"));
			data.put("sex", new String("sex"));
		
			new HttpClient().postData("http://localhost:8980/reptile/a/ymx/spiderResultAPi/spiderResultAPi", data, new HttpDao() {
				@Override
				public void requestFinish(String result) {
					System.out.println(result);
				}
				@Override
				public void requestFail(String result) {
					System.out.println(result);

				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		} 
		System.out.println("数据提交完毕");
	} 
	 
	/*
	 * 卸载
	 */
	public void disconnect() { 
	/*	if (!this.isInterrupted()) {
			this.interrupt();
		}*/
	}
	 
	
}
