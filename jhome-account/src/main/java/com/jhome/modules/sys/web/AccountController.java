package com.jhome.modules.sys.web;

import com.daxu.common.Bus.ResponseJson;
import com.daxu.common.Identity.UserUtil;
import com.daxu.common.ToolKit.JSONUtils;
import com.daxu.common.ToolKit.StringUtil;
import com.jhome.common.shiro.realm.DeviceType;
import com.jhome.common.shiro.realm.SessionCons;
import com.jhome.common.shiro.realm.jhomeToken;
import com.jhome.modules.sys.model.po.UserInfo;
import com.jhome.modules.sys.web.baseController.baseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "sys登录控制器")
@Controller
@RequestMapping("/${adminPath}")
public class AccountController extends baseController {
    @GetMapping("/login")
    public String login() {
        return "modules/sys/login";
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "account", paramType = "string", required = true, dataType = "User"),
            @ApiImplicitParam(name = "password", value = "password", paramType = "string", required = true, dataType = "User")
    })
    public ResponseJson login(HttpServletRequest request, HttpServletResponse response, @RequestBody UserInfo user) {
        try {
            //MemcachedManager.InitMemCache("");
            System.out.println("登录请求~~~~");
            try {
                Subject subject = SecurityUtils.getSubject();
                //默认用户信息
                //UsernamePasswordToken token =new UsernamePasswordToken(user.getAccount(),user.getPassword());
                //自定义多个realm
                jhomeToken jhomeToken = new jhomeToken(user.getAccount(), user.getPassword(), 0, DeviceType.PC.toString());
                jhomeToken.setRememberMe(true);
                subject.login(jhomeToken);
                //UserEntity user2=(UserEntity) subject.getPrincipal();
                if (subject.isAuthenticated()) {

                    //3.取出Shiro中保存的用户信息
                    UserInfo userInfo = (UserInfo) subject.getPrincipal();
                    Session session = subject.getSession();
                    session.setAttribute(SessionCons.DEVICE_TYPE, DeviceType.PC.toString());
                    //4.将用户信息保存到session中
                    session.setAttribute(SessionCons.LOGIN_USER_SESSION, JSONUtils.beanToJson(userInfo));
                    String tokenStr = UserUtil.GetToken((String) session.getId());
                    String backUrl = (String) session.getAttribute("fallbackUrl");
                    if (StringUtil.isNotBlank(backUrl))
                        return new ResponseJson().success().setValue("backUrl", String.format("%s?token=%s", backUrl, tokenStr));
                    return new ResponseJson().success().setValue("backUrl", "/jhome/account/main");
                }
                /**
                 * 返回  模式 Token认证
                 */
                //result.data= AuthUtil.userLogin(String.valueOf(user.getId()),user, response);

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                return new ResponseJson().error(e.getMessage().toString());
            }
        } catch (Exception ex) {
            return new ResponseJson().error(ex.getMessage().toString());
        }
        return null;
    }


    //退出
    @GetMapping("/logout")
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
