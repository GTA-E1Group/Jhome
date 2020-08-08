package com.bracket.common.Http;

public interface HttpDao {
  public void requestFinish(String result);//请求完成
  public void requestFail(String result);//请求失败
}
