package com.jhome.modules.sys.dao.impl;

import com.daxu.common.Identity.UserUtil;
import com.jhome.modules.sys.dao.CrudDao;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @program: jhome-root
 * @description
 * @author: Daxv
 * @create: 2020-06-13 17:42
 **/
public class CrudDaoImpl implements CrudDao {
    @Autowired
    public RestTemplate client;

    /**
     * JSONObject jsonObject=new JSONObject();
     * jsonObject.put("k","v");
     *
     * @param url
     * @param params
     * @param type
     * @param <T>
     * @return
     */
    @Override
    public <T> ResponseEntity<T> LoginByAccount(String url, HttpMethod method, JSONObject params, Class<T> type) {
        HttpHeaders httpHeaders = new HttpHeaders();
        //表单方式提交
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set(HttpHeaders.ACCEPT, "application/json;charset=UTF-8");
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        //将请求头和参数合并成一个请求
        HttpEntity<JSONObject> requestEntity = new HttpEntity(params.toString(), httpHeaders);
        ResponseEntity<T> responseEntity = client.exchange(url, method, requestEntity, type);
        //JSONObject jsonObject= (JSONObject) client.patchForObject(url,requestEntity,type);
        return responseEntity;
    }

    @Override
    public void LoginOut() {
        UserUtil.loginOut();
    }
}
