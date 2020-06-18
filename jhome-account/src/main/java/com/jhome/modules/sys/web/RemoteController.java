package com.jhome.modules.sys.web;

import com.alibaba.fastjson.JSON;
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
    @ResponseBody
    public String getSession(@RequestParam("sessionId") String sessionId) {
        try {
            ShiroSession shiroSession = (ShiroSession) remoteService.getSession(sessionId);
            return JSONUtils.beanToJson(shiroSession);
        } catch (Exception ex) {
            logger.error(String.format("createSession error :%s", ex.getMessage().toString()));
        }
        return "";
    }

    @PostMapping(value = "/createSession")
    @ResponseBody
    public String createSession(@RequestParam("sessionJson") String sessionJson) {
        try {
            ShiroSession shiroSession = JSON.parseObject(sessionJson, ShiroSession.class);
            SessionDaoZH.SerializedStringToAttributeBean(shiroSession);
            Serializable sessionId = remoteService.createSession(shiroSession);
            return (String) sessionId;
        } catch (Exception ex) {
            logger.error(String.format("createSession error :%s", ex.getMessage().toString()));
        }
        return "";
    }

    @PostMapping(value = "/updateSession")
    @ResponseBody
    public String updateSession(@RequestParam("sessionJson") String sessionJson) {
        try {
            ShiroSession shiroSession = JSON.parseObject(sessionJson, ShiroSession.class);
            SessionDaoZH.SerializedStringToAttributeBean(shiroSession);
            remoteService.updateSession(shiroSession);
            return String.format("UpdateSession success :200");

        } catch (Exception ex) {
            logger.error(String.format("UpdateSession error :%s", ex.getMessage().toString()));
            return String.format("UpdateSession error :%s", ex.getMessage().toString());
        }
    }

    @PostMapping(value = "/deleteSession")
    @ResponseBody
    public String deleteSession(@RequestParam("sessionJson") String sessionJson) {
        try {
            //通过接口远程成功后再销毁本地Session
            if (1 == 1) {
            }
            ShiroSession shiroSession = JSON.parseObject(sessionJson, ShiroSession.class);
            remoteService.deleteSession(shiroSession);
            return "true";
        } catch (Exception ex) {
            logger.error(String.format("deleteSession error :%s", ex.getMessage().toString()));
            return "false";
        }
    }

    @PostMapping(value = "/getPermissions")
    @ResponseBody
    public PermissionContext getPermissions(String username) {
        return remoteService.getPermissions(username);
    }

}
