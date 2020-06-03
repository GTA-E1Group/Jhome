package com.jhome.modules.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class payController {

    @GetMapping("/index")
    public String index() {
        System.out.println("1111111111111111");
        return "/modules/sys/pay";
    }
}
