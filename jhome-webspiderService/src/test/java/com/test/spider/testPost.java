package com.test.spider;


import com.daxu.common.Http.HttpClient;
import com.daxu.common.Http.HttpDao;
import junit.framework.TestCase;

public class testPost extends TestCase {

	public void testPostMethon() {
		try {
			//返回请求结果
		/*	HttpClient hcClient=new HttpClient(new HttpDao() {
				
				public void requestFinish(String result) {
					// TODO Auto-generated method stub
					System.out.println(result);
				}
				public void requestFail(String result) {
					// TODO Auto-generated method stub
					System.out.println(result);
					
				}
			});
			hcClient.postData("http://localhost:8980/reptile/a/ymx/spiderResultAPi/spiderResultAPi", null);*/
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
