package com.jhome.modules.sys.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.bracket.common.Bus.AbstractDaoImpl.HttpDaoImpl;
import com.bracket.common.Identity.UserUtil;
import com.jhome.modules.sys.dao.CrudDao;
import com.other.common.utils.excel.annotation.ExcelField;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * @program: jhome-root
 * @description
 * @author: Daxv
 * @create: 2020-06-13 17:42
 **/
public class CrudDaoImpl extends HttpDaoImpl implements CrudDao {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RestTemplate client;

    /**
     * 参数传递
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
        try {
            return super.SendByPostRequest(url, method, params, type, client);
        } catch (Exception ex) {
            logger.error(String.format("单点登录失败：%s", ex.getMessage()));
            return null;
        }
    }

    @Override
    public <T> ResponseEntity<T> LoginOut(String url, JSONObject params, Class<T> type) {
        try {
            return super.SendByGetRequest(url, params, type, client);
        } catch (Exception ex) {
            logger.error(String.format("注销失败：%s", ex.getMessage()));
            return null;
        }
    }
}
