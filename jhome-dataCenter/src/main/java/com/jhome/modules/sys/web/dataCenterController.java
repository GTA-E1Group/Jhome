package com.jhome.modules.sys.web;


import com.daxu.common.Bus.ResponResult;
import com.daxu.common.ToolKit.JSONUtils;
import com.jhome.modules.sys.pojo.UserInfo;
import com.jhome.modules.sys.web.baseController.baseController;
import com.rpc.common.thrift.socketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("数据中心")
@RestController
@RequestMapping("${jhome.sysproperties.adminPath}/")
public class dataCenterController extends baseController {

    @Autowired
    private TTransport tTransport;
    @Autowired
    private  socketService.Client client;



    @GetMapping("/getDataCenterUrl")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "数据中心名称", paramType = "string", required = true, dataType = "String")
    })
    public ResponResult getDataCenterUrl(String name)
    {
        ResponResult result=new ResponResult();
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session= subject.getSession();

/*            String jsonString= (String) session.getAttribute("session_login_user");
            UserInfo userInfo= (UserInfo) JSONUtils.jsonToBean(jsonString,UserInfo.class);
            result.setData(userInfo);*/

            try {
                tTransport.open();
                client.singleSend("{userId:daxu}");
            }
            catch (Exception ex )
            {

            }
            finally {
                tTransport.close();
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage().toString());
        }
        return  result;
    }

}
