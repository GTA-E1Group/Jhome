package com.daxu.common.Identity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.daxu.common.ToolKit.CookieUtil;
import com.daxu.common.ToolKit.JSONUtils;
import com.daxu.common.ToolKit.StringUtil;
import com.domain.common.UserInfo;
import com.shiro.common.session.ShiroSession;
import com.shiro.common.token.DeviceType;
import io.buji.pac4j.subject.Pac4jPrincipal;
import javafx.scene.control.TableRow;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户帮助类
 *
 * @program: jhome-root
 * @description
 * @author: Daxv
 * @create: 2020-06-04 22:10
 **/

public class UserUtil extends AuthUtil {
    protected static Logger logger = LoggerFactory.getLogger(UserUtil.class);
    public static Subject subject;

    /**
     * 获取token
     *
     * @param sessionId
     * @return
     */
    public static String GetToken(String sessionId) {
        if (sessionId.isEmpty()) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", sessionId);
        String token = JavaWebToken.createJavaWebToken(map);
        return token;
    }

    /**
     * 解析Token
     *
     * @param token
     * @return
     */
    public static Object ParsingToken(String token) {
        if (token.isEmpty()) {
            return null;
        }
        Map<String, Object> tokenMap = decodeSession(token);
        return tokenMap.get("token");
    }

    /**
     * 获取Subject
     *
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 登陆
     *
     * @param token
     */
    public static void Login(UsernamePasswordToken token) {
        SecurityUtils.getSubject().login(token);
    }

    /**
     * 获取会话
     *
     * @return
     */
    public static Session GetSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * {"deviceType":"UcCas","gender":"1","loginName":"admin2","luxToken":"Tp3a353216-bf67-46a9-ac1e-015ba8a8b3e9","phone":"14738490400","productId":"1271318084763017218","realName":"管理员","remotelyToken":"PC-8178134ede762c42e41a7b1695082e28-666-20200720160651-2c2de0","roleNames":"学生,超级管理员","schoolName":"国泰安大学","userId":"666"}     * 获取用户信息1
     *
     * @return json
     */
    public static UserInfo GetUserInfo(UserInfoHandler userInfoHandler) {
        try {
            UserInfo userInfo = new UserInfo();
            String userInfoJson = (String) SecurityUtils
                    .getSubject()
                    .getSession()
                    .getAttribute("session_login_user");
            if (StringUtil.isBlank(userInfoJson)) {
                Session sessionPac4j = SecurityUtils.getSubject().getSession();//Pac4j session
                String sessionId = (String) sessionPac4j.getId();
                //String userJson = JSONUtils.beanToJson(UserUtil.getSubject().getPrincipal());
                ShiroSession shiroSession = (ShiroSession) userInfoHandler.invoke(sessionId);
                userInfoJson = (String) shiroSession.getAttribute("session_login_user");
            }
            //remoteService.getSession(sessionPac4jId)
            JSONObject userObj = (JSONObject) JSONObject.parse(userInfoJson);
            userInfo.setDeviceType(DeviceType.CAS.toString());
            userInfo.setUserId(userObj.getString("userId"));
            userInfo.setLoginName(userObj.getString("loginName"));
            userInfo.setJhomeToken(userObj.getString("jhomeToken"));
            //return (UserInfo) JSONUtils.jsonToBean(userInfoJson, UserInfo.class); 报错，不知道为啥，后续优化；
            return userInfo;
        } catch (Exception ex) {
            logger.debug("获取用户报错：%s", ex.getMessage());
        }
        return null;
    }

    /**
     * @Description 读取token
     * @Author daxv
     * @Date
     * @Remarks ...
     */

    public static String GetToken(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String token = StringUtil.isNotBlank(httpServletRequest.getHeader("JhomeToken")) ? httpServletRequest.getHeader("JhomeToken") : httpServletRequest.getParameter("JhomeToken");
        if (StringUtil.isBlank(token))
            token = (String) request.getAttribute("org.apache.shiro.web.servlet.ShiroHttpServletRequest_REQUESTED_SESSION_ID");
        if (StringUtil.isBlank(token)) {
            Cookie cookie = CookieUtil.get(httpServletRequest, "JhomeCookie");
            if (cookie != null) {
                token = cookie.getValue();
            }
        }
        return token;
    }
    /**
     * 获取用户信息2
     *
     * @return T 格式化
     */
    public static <T> T GetUserInfoByBean(UserInfoCertification userInfoCertification, Class<T> type) throws Exception {
        if (type == null)
            throw new Exception();
        String userInfoJson = (String) SecurityUtils
                .getSubject()
                .getSession()
                .getAttribute("session_login_user")
                .toString();
        String userInfoStr = userInfoCertification.invoke((JSONObject) JSONObject.parse(userInfoJson));
        return JSON.parseObject(userInfoStr, type);
    }

    /**
     * 用户退出
     *
     * @return
     */
    public static boolean loginOut() {
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                subject.logout();
            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * 获取 Pac4 回调用户信息
     *
     * @return
     */
    public static String GetCasPac4jInfo(HttpServletRequest request, HttpServletResponse response) {
        String loginName = "";
        CommonProfile obj = (CommonProfile) request.getAttribute("pac4jUserProfiles");
        Pac4jPrincipal principal = (Pac4jPrincipal) request.getUserPrincipal();
        if (principal == null)
            loginName = obj.getId();
        else
            loginName = principal.getProfile().getId();
        return loginName;
    }
}
