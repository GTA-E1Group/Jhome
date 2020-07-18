package com.jhome.modules.sys.web;

import com.alibaba.fastjson.JSONObject;
import com.daxu.common.Bus.ResponseJson;
import com.daxu.common.Identity.UserUtil;
import com.daxu.common.ToolKit.JSONUtils;
import com.daxu.common.ToolKit.StringUtil;
import com.domain.common.UserInfo;
import com.jhome.modules.sys.web.baseController.baseController;
import com.shiro.common.realm.SessionCons;
import com.shiro.common.token.DeviceType;
import com.shiro.common.token.jhomeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "z、账户服务-登录API")
@RestController
@RequestMapping("/${adminPath}")
public class AccountController extends baseController {
    @GetMapping("/login")
    public String login() {
        return "modules/sys/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String login(HttpServletRequest request, HttpServletResponse response, @RequestBody UserInfo user) {
        try {
            if (user == null || StringUtil.isBlank(user.getLoginName()) || StringUtil.isBlank(user.getPassword()))
                return JSONUtils.beanToJson(new ResponseJson().error("用户名密码不能为空！"));
            if (!DeviceType.toList().contains(user.getDeviceType()))
                return JSONUtils.beanToJson(new ResponseJson().error("登陆设备类型不能存在！"));
            Subject subject = SecurityUtils.getSubject();
            jhomeToken token = new jhomeToken(user.getLoginName(), user.getPassword(), 0, user.getDeviceType());
            token.setRememberMe(true);
            subject.login(token);
            if (subject.isAuthenticated()) {
                String userJson = (String) subject.getPrincipal();
                Session session = subject.getSession();
                session.setAttribute(SessionCons.DEVICE_TYPE, user.getDeviceType());
                session.setAttribute(SessionCons.LOGIN_USER_SESSION, userJson);
                String tokenStr = (String) session.getId();
                JSONObject jsonObject = (JSONObject) JSONObject.parse(userJson);

                if (jsonObject.getString("returnCode").equals("200")) {
                    return new ResponseJson()
                            .success()
                            .setValue("data", JSONUtils.jsonToMap(jsonObject.getString("data")))
                            .setData("jhomeToken", tokenStr)
                            .toString();
                } else {
                    return new ResponseJson()
                            .error("用户登陆失败")
                            .toString();
                }
            }
        } catch (Exception ex) {
            return new ResponseJson().error("登陆失败").toString();
        }
        return null;
    }


    //退出
    @RequestMapping(value = "/logout",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return "redirect:account/login";
        //return "forward:/a/sys/login";
    }

    @GetMapping("/main")
    public String main() {
        return "modules/sys/main";
    }
}
