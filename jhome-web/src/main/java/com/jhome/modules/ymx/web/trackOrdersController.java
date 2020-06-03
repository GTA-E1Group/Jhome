package com.jhome.modules.ymx.web;

import com.daxu.common.Bus.IBus;
import com.daxu.common.Bus.PushInfo;
import com.daxu.common.Queue.Bus;
import com.daxu.common.Queue.QueueHandler;
import com.jhome.modules.ymx.web.baseController.baseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Api(tags = "ymx请求控制器")
@Controller
@RequestMapping("${frontPath}")
public class trackOrdersController extends baseController {



    @Autowired
    public  Bus bus;
//    @Autowired
//    public SysConfigurationPropertiesBean pro;


/*    @Autowired
    private RemoteServiceInterface remoteService;*/
    //@Compensable(confirmMethod = "tccMember", cancelMethod = "tccMember", propagation = Propagation.SUPPORTS)
    @ApiOperation(value = "首页接口", notes = "首页接口文档详情")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String trackOrdersIndex(Model model) {
        try {
            System.out.println(String.format("容器注入BUS 成功！类名称：%s",bus.getClass().toString()));
            System.out.println("你好我来了");
           // System.out.println(String.format("后端路径表示：%s 前端路径标识：%s", pro.getAdminPath(), pro.getFrontPath()));

        } catch (Exception ex) {

        }

        return "modules/ymx/index";
    }

    /*
     * 查询
     */
    @ResponseBody
    @RequestMapping(value = "/spiderApi{name}", method = RequestMethod.POST)
    @ApiOperation(value = "爬虫查询接口", notes = "爬虫查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telephone", value = "查询内容", paramType = "query", required = true, dataType = "String")
    })
    public String spiderApi(@PathVariable("name") String name) {
        try {

            System.out.println("sping");
            bus.AddMessAgeByQueue(new IBus() {
                @Override
                public void doQueueHandle(QueueHandler queueManager) {
                    try {
                        for (int i = 0; i < 1; i++) {
                            PushInfo info = new PushInfo();
                            info.setMessageBody(i);
                            info.setJpushTime(new Date());
                            if (queueManager.AddProducerMQ("daxu", info)) {
                                System.out.println("发送成功");
                            } else {
                                System.out.println("发送失败了");
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            });
/*            new Bus(new Config(pro.getQueueconfig().getHostName(), pro.getQueueconfig().getUserName(), pro.getQueueconfig().getPassWord(), pro.getQueueconfig().getVirtualHost()), new IBus() {
                public void doQueueHandle(QueueHandler queueManager) {
                    try {
                        for (int i = 0; i < 1; i++) {
                            PushInfo info = new PushInfo();
                            info.setMessageBody(i);
                            info.setJpushTime(new Date());
                            if (queueManager.AddProducerMQ("daxu", info)) {
                                System.out.println("发送成功");
                            } else {
                                System.out.println("发送失败了");
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }).Send();*/
            System.out.println("~~~~~~~~~~~推送完毕~~~~~~~~~~~~~~·");
        } catch (Exception e) {
            // TODO: handle exception
        }
        return name;
    }
}
