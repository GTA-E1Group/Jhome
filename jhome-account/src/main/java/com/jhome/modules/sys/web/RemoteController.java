package com.jhome.modules.sys.web;

import com.beust.jcommander.Parameter;
import com.daxu.common.Bus.RequestResult;
import com.daxu.common.Bus.ResponResult;
import com.daxu.common.ToolKit.JSONUtils;
import com.domain.common.PermissionContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhome.modules.sys.service.RemoteService;
import com.jhome.modules.sys.web.baseController.baseController;
import com.shiro.common.SessionDaoZH;
import com.shiro.common.session.ShiroSession;
import io.swagger.annotations.Api;
import javafx.scene.shape.VLineTo;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Api(tags = "服务器远程调用接口")
@Controller
@RequestMapping("/${foreignServerPath}")
public class RemoteController extends baseController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    public RemoteService remoteService;

    @PostMapping(value = "/getSession")
    public @ResponseBody String getSession(@RequestParam("appKey")  String appKey,@RequestParam("sessionId")  String sessionId) {
        ShiroSession shiroSession=(ShiroSession) remoteService.getSession(appKey, sessionId);
        String shiroSessionJson=JSONUtils.beanToJson(shiroSession);
        //ShiroSession sh=(ShiroSession) SessionDaoZH.SerializedBeanToStringByRemoteService(shiroSession);
        return shiroSessionJson;
    }

    @PostMapping(value = "/createSession")
    @ResponseBody
    public ResponResult createSession(@RequestBody ShiroSession session) {
        ResponResult responResult=new ResponResult();
        try {
            Serializable sessionId=remoteService.createSession(session);
            responResult.setData(sessionId);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());

        }
        return responResult;
    }

    @PostMapping(value = "/updateSession")
    @ResponseBody
    public void updateSession(@RequestParam("appKey")  String appKey, @RequestBody ShiroSession shiroSession) {
        try {
            Session session= shiroSession;
            remoteService.updateSession(appKey, session);

        } catch (Exception ex) {
            System.out.println(ex.getMessage().toString());
        }
       // return responResult;
    }

    @PostMapping(value = "/deleteSession")
    @ResponseBody
    public boolean deleteSession(@RequestParam("appKey")  String appKey,@RequestBody RequestResult result) {
        boolean falg = false;
        try {
            remoteService.deleteSession(appKey, (Session)result.getData());
            falg = true;

        } catch (Exception ex) {
        }
        return falg;
    }

    @PostMapping(value = "/getPermissions")
    @ResponseBody
    public PermissionContext getPermissions(String appKey, String username) {
        return remoteService.getPermissions(appKey, username);
    }

}
