package com.jhome.modules.ymx.web.baseController;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局处理
 */
@ControllerAdvice
public class globalDataPreprocessingController {
    /**
     * 统一异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
//    @ExceptionHandler(value = {java.lang.NullPointerException.class,java.lang.Exception.class,})
    public String exceptionHandler(Exception ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", ex.getMessage());
        System.out.println(ex.getMessage());
        return "forward:/error";
    }

    /**
     * 全局数据绑定
     *
     * @return
     */
    @ModelAttribute(name = "userInfo")
    public Map<String, Object> userInfo() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "daxv");
        return map;
    }


}
