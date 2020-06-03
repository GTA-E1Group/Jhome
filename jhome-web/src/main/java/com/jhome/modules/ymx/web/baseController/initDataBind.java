package com.jhome.modules.ymx.web.baseController;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;


/**
 * 初始化数据绑定器
 */
@ControllerAdvice
public class initDataBind extends globalDataPreprocessingController {
    @InitBinder("b")
    public void b(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("b.");
    }

    @InitBinder("a")
    public void a(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("a.");
    }

}
