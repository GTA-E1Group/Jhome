package com.jhome.modules.sys.dao.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * @program: jhome-root
 * @description
 * @author: Daxv
 * @create: 2020-06-20 17:49
 **/
public class BaseDaoImpl {
    public <T> ResponseEntity<T> ToPost(RestTemplate client, String url, HttpMethod method, JSONObject params, Class<T> type) {

            HttpHeaders httpHeaders = new HttpHeaders();
            //表单方式提交
            httpHeaders.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
            //httpHeaders.set(HttpHeaders.ACCEPT, "application/json;charset=UTF-8");
            httpHeaders.set("Accept", "*/*");
            httpHeaders.set("Accept-Charset", "utf-8");
            //httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            //将请求头和参数合并成一个请求
            HttpEntity<String> requestEntity = new HttpEntity(params.toString(), httpHeaders);
            ResponseEntity<T> responseEntity = client.exchange(url, method, requestEntity, type);
            //JSONObject jsonObject= (JSONObject) client.patchForObject(url,requestEntity,type);
            return responseEntity;
    }

}
