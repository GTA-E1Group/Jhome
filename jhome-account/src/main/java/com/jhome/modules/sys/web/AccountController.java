package com.jhome.modules.sys.web;

import com.daxu.common.Bus.ResponResult;
import com.daxu.common.Cache.MemcachedManager;
import com.daxu.common.Identity.AuthUtil;
import com.daxu.common.Identity.UserUtil;
import com.daxu.common.RedissonHandler.RedissonHandler;
import com.daxu.common.ToolKit.JSONUtils;
import com.jhome.common.shiro.realm.DeviceType;
import com.jhome.common.shiro.realm.SessionCons;
import com.jhome.common.shiro.realm.jhomeToken;
import com.jhome.modules.sys.pojo.UserInfo;
import com.jhome.modules.sys.web.baseController.baseController;
import com.shiro.common.session.ShiroSession;
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
    public ResponResult login(HttpServletRequest request, HttpServletResponse response, @RequestBody UserInfo user) {
        ResponResult result = new ResponResult();
        try {
            MemcachedManager.InitMemCache("");
            result.code = "1";
            System.out.println("登录请求~~~~");
            try {
                Subject subject = SecurityUtils.getSubject();
                //默认用户信息
                //UsernamePasswordToken token =new UsernamePasswordToken(user.getAccount(),user.getPassword());
                //自定义多个realm
                jhomeToken token = new jhomeToken(user.getAccount(), user.getPassword(), 0, DeviceType.PC.toString());
                token.setRememberMe(true);
                subject.login(token);
                //UserEntity user2=(UserEntity) subject.getPrincipal();
                if (subject.isAuthenticated()) {

                    //3.取出Shiro中保存的用户信息
                    UserInfo userInfo = (UserInfo) subject.getPrincipal();
                    Session session= subject.getSession();
                    session.setAttribute(SessionCons.DEVICE_TYPE,DeviceType.PC.toString());
                    //4.将用户信息保存到session中
                    session.setAttribute(SessionCons.LOGIN_USER_SESSION, JSONUtils.beanToJson(userInfo) );
                    result.setMsg("登陆成功");
                    result.setCode("1");
                    result.data=String.format("http://127.0.0.1:8111/jhome/web/index?token=%s",UserUtil.GetToken((String) session.getId())) ;

                }

                /**
                 * 返回  模式 Token认证
                 */
                //result.data= AuthUtil.userLogin(String.valueOf(user.getId()),user, response);

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                result.code = "-1";
                result.msg = "登陆失败";
            }
        } catch (Exception ex) {
            result.setCode("1");
            result.setMsg(ex.getMessage());
        }
        return result;
    }



    //退出
    @GetMapping("/logout")
    public String logout() {
        Subject subject=SecurityUtils.getSubject();
        if(subject.isAuthenticated())
        {
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
