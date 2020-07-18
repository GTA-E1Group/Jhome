package com.daxu.common.Bus;

import com.daxu.common.ToolKit.JSONUtils;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @author : Daxv
 * @date : 16:34 2020/5/15 0015
 */

public class ResponseJson extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    private static final Integer SUCCESS_STATUS = 200;
    private static final Integer ERROR_STATUS = 401;
    private static final String SUCCESS_MSG = "一切正常";

    public ResponseJson() {
        super();
    }

    public ResponseJson(int code) {
        super();
        this.setStatus(code);
    }

    public ResponseJson success() {
        put("msg", SUCCESS_MSG);
        put("status", SUCCESS_STATUS);
        return this;
    }
    public ResponseJson successByMessAge(String messAge) {
        put("msg", messAge);
        put("status", SUCCESS_STATUS);
        return this;
    }
    //后续使用 Shrio框架
    public ResponseJson success(HttpStatus status) {
        put("msg", SUCCESS_MSG);
        put("status", SUCCESS_STATUS);
        return this;
    }

    public ResponseJson success(String msg) {
        put("msg", msg);
        put("status", SUCCESS_STATUS);
        return this;
    }

    public ResponseJson error(String msg) {
        put("msg", msg);
        put("status", ERROR_STATUS);
        return this;
    }

    public ResponseJson setData(String key, Object obj) {
        HashMap<String, Object> data = (HashMap<String, Object>) get("data");
        if (data == null) {
            data = new HashMap<String, Object>();
            put("data", data);

        }
        data.put(key, obj);
        return this;
    }

    public ResponseJson setStatus(int status) {
        put("status", status);
        return this;
    }

    public ResponseJson setMsg(String msg) {
        put("msg", msg);
        return this;
    }

    public ResponseJson setValue(String key, Object val) {
        put(key, val);
        return this;
    }

    @Override
    public String toString() {
        return JSONUtils.beanToJson(this);
    }
}
