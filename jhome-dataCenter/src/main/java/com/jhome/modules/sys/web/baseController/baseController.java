package com.jhome.modules.sys.web.baseController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制器基础类
 */
public abstract class baseController {
    protected static final String FORWARD = "forward:";
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected static final String REDIRECT = "redirect:";

    @Value("${jhome.sysproperties.adminPath}")
    protected String adminPath;

    public baseController() {
    }

}
