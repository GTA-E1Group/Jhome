package com.daxu.common.Http;

public interface HttpDaoExpansion extends HttpDao {
	  public void requestProgress(String result);//正在请求
	  public void request(String result);//下载完成
	  public void requestGetDestinationUrl(String result);//下载过程  
}
