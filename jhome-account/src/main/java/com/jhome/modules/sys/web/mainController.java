package com.jhome.modules.sys.web;

import com.rpc.common.thrift.socketService;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mt")
public class mainController {
    @Autowired
    private TTransport tTransport;
    @Autowired
    private socketService.Client client;

    @GetMapping("/main")
    public String main() {

        try {
            tTransport.open();
            client.singleSend("{userId:daxu}");
        } catch (Exception ex) {

        } finally {
            tTransport.close();
        }

        return "/modules/sys/main";
    }
}
