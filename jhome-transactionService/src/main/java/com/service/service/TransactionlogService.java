package com.service.service;

import com.alibaba.fastjson.JSONObject;
import com.service.model.Transactionlog;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface TransactionlogService {
    /**
     * 保存用户
     *
     * @param transactionLog
     */
    boolean save(Transactionlog transactionLog);


    /**
     * 修改
     *
     * @param transactionLog
     */
    boolean update(Transactionlog transactionLog);

    /**
     * 删除
     *
     * @param id
     */
    boolean delete(String id);

    /**
     * 查询全部配置
     *
     * @return
     */
    List<Transactionlog> findAll();

    /**
     * 根据条件查询
     *
     * @param
     * @return
     */
    List<Transactionlog> getList(Transactionlog Transactionlog);

    <T> ResponseEntity<T> SendByGetRequest(String url, JSONObject params, Class<T> type, RestTemplate client);

    <T> ResponseEntity<T> SendByPostRequest(String url, HttpMethod method, JSONObject params, Class<T> type, RestTemplate client);
}
