package com.jhome.modules.sys.dao.impl;

import com.daxu.common.Identity.UserUtil;
import com.jhome.modules.sys.dao.CrudDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
     * 
     * @param url
     * @param params
     * @param type
     * @param <T>
     * @return
     */
    @Override
    public <T> ResponseEntity<T> LoginByAccount(String url, MultiValueMap<String, String> params, Class<T> type) {
        HttpHeaders httpHeaders=new HttpHeaders();
        HttpMethod method=HttpMethod.POST;
        //表单方式提交
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头和参数合并成一个请求
        HttpEntity<MultiValueMap<String,String>>requestEntity=new HttpEntity<>(params,httpHeaders);
        ResponseEntity<T> responseEntity= client.exchange(url,method,requestEntity,type);
        return responseEntity;
    }

    @Override
    public void LoginOut() {
        UserUtil.loginOut();
    }
}
