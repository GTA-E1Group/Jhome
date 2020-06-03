package com.jhome.modules.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mt")
public class mainController {
    @GetMapping("/main")
    public String main() {
        return "/modules/sys/main";
    }
}
