package com.jhome.modules.sys.web;

import com.daxu.common.Bus.ResponseJson;
import com.daxu.common.Identity.UserUtil;
import com.daxu.common.ToolKit.StringUtil;
import com.jhome.common.shiro.realm.DeviceType;
import com.jhome.common.shiro.realm.jhomeToken;
import com.other.common.codec.EncodeUtils;
import com.other.common.web.http.ServletUtils;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "Z、账户服务-token认证")
@RestController
public class SsoController {
    /**
     * 单点登录（如已经登录，则直接跳转）
     * <p>
     * username         登录用户名（loginCode）
     * token            单点登录令牌，令牌组成：sso密钥+用户名+日期，进行md5加密，举例：
     * // 注意如果 shiro.sso.encryptKey 为 true，则 secretKey 会自动加密。
     * String secretKey = Global.getConfig("shiro.sso.secretKey");
     * String token = Md5Utils.md5(secretKey + username + DateUtils.getDate("yyyyMMdd"));
     * （JSON格式），或 param_ 前缀的请求参数。
     * url              登录成功后跳转的url地址。
     * 是否强制重新登录，需要强制重新登录传递true
     * ：
     * http://localhost/project/sso/{username}/{token}?url=/sys/user/list?p1=v1%26p2=v2&relogin=true
     * 如果url中携带参数，请使用转义字符，如“&”号，使用“%26”转义。
     */
    @GetMapping(value = "/sso/{type}/{token}")
    public String sso(@PathVariable String token,
                      @PathVariable String type,
                      HttpServletRequest request,
                      HttpServletResponse response) {
        if (StringUtil.isBlank(token))
            return new ResponseJson().error("token不能为空！").toString();
        if (StringUtil.isBlank(type))
            return new ResponseJson().error("登录设备类型不能为空！").toString();

        if (!DeviceType.toList().contains(type))
            return new ResponseJson().error("登录设备类型不存在！").toString();

        try {
            // 如果已经登录，并且是同一个人，并且不强制重新登录，则直接跳转到目标页
            /*
            User user = UserUtils.getUser();
            if(StringUtils.isNotBlank(user.getUserCode())
                    && StringUtils.equals(user.getLoginCode(), username)
                    && !ObjectUtils.toBoolean(relogin)){
                if (ServletUtils.isAjaxRequest(request)){
                    return ServletUtils.renderResult(response, Global.TRUE, text("账号已登录"));
                }else{
                    return REDIRECT + EncodeUtils.decodeUrl2(url);
                }
            }*/

            // 通过令牌登录系统
            jhomeToken jhomeToken = new jhomeToken();
            jhomeToken.setDeviceType(type);
            jhomeToken.setSsoToken(token);
            UserUtil.getSubject().login(jhomeToken);
            if (UserUtil.getSubject().isAuthenticated())
                return new ResponseJson()
                        .success()
                        .setValue("token", UserUtil.getSubject()
                                .getSession()
                                .getId())
                        .toString();
        } catch (AuthenticationException e) {
            return new ResponseJson().error("msg:登录失败，请联系管理员！").toString();

        }
        return new ResponseJson().error("msg:登录失败，请联系管理员！").toString();

    }



    /*@RequestMapping(value = "sso/{username}/{token}")
    public String sso(@PathVariable String username, @PathVariable String token,
                      @RequestParam(defaultValue="${adminPath}") String url, String relogin,
                      HttpServletRequest request, HttpServletResponse response, com.nettyServer.Model model){
    // 如果已经登录，并且是同一个人，并且不强制重新登录，则直接跳转到目标页
        User user = UserUtils.getUser();
        if(StringUtils.isNotBlank(user.getUserCode())
                && StringUtils.equals(user.getLoginCode(), username)
                && !ObjectUtils.toBoolean(relogin)){
            if (ServletUtils.isAjaxRequest(request)){
                return ServletUtils.renderResult(response, Global.TRUE, text("账号已登录"));
            }else{
                return REDIRECT + EncodeUtils.decodeUrl2(url);
            }
        }
        // 通过令牌登录系统
        if (token != null){
            try {
                jhomeToken upToken = new jhomeToken();
                upToken.setUsername(username);	// 登录用户名
                upToken.setSsoToken(token); 	// 单点登录令牌
                upToken.setParams(ServletUtils.getExtParams(request)); // 登录附加参数
                UserUtils.getSubject().login(upToken);
                if (ServletUtils.isAjaxRequest(request)){
                    return ServletUtils.renderResult(response, Global.TRUE, text("账号登录成功"));
                }else{
                    return REDIRECT + EncodeUtils.decodeUrl2(url);
                }
            } catch (AuthenticationException e) {
                if (!e.getMessage().startsWith("msg:")){
                    throw new AuthenticationException("msg:登录失败，请联系管理员。", e);
                }
                throw e;
            }
        }
        return "error/403";
    }*/
}
