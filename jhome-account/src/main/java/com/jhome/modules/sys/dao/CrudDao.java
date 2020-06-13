package com.jhome.modules.sys.dao;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * @Description 基类接口
 * @Author daxv
 * @Date
 * @Remarks ...
 */
public interface CrudDao {

    /**
     *  @Description 单点登录接口
     *  @Author daxv
     *  @Date
     *  @Remarks ...
     */
    public <T> ResponseEntity<T> LoginByAccount(String url, MultiValueMap<String, String> params, Class<T> type);

    /**
     *  @Description 退出
     *  @Author daxv
     *  @Date
     *  @Remarks ...
     */
    void LoginOut();
}
