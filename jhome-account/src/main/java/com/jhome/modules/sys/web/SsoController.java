package com.jhome.modules.sys.web;

import com.jhome.common.shiro.realm.jhomeToken;
import com.other.common.codec.EncodeUtils;
import com.other.common.web.http.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SsoController {

    /**
     * 单点登录（如已经登录，则直接跳转）
     * @param username 	登录用户名（loginCode）
     * @param token 	单点登录令牌，令牌组成：sso密钥+用户名+日期，进行md5加密，举例：
     * 		// 注意如果 shiro.sso.encryptKey 为 true，则 secretKey 会自动加密。
     * 		String secretKey = Global.getConfig("shiro.sso.secretKey");
     * 		String token = Md5Utils.md5(secretKey + username + DateUtils.getDate("yyyyMMdd"));
     * @param   	登录附加参数（JSON格式），或 param_ 前缀的请求参数。
     * @param url 		登录成功后跳转的url地址。
     * @param relogin 	是否强制重新登录，需要强制重新登录传递true
     * @see  ：
     * 	http://localhost/project/sso/{username}/{token}?url=/sys/user/list?p1=v1%26p2=v2&relogin=true
     * 	如果url中携带参数，请使用转义字符，如“&”号，使用“%26”转义。
     */
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
