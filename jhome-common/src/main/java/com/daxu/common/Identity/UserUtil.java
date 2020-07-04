package com.daxu.common.Identity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.scene.control.TableRow;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

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
    public static Subject subject;

    /**
     * 根据 session 生成Token
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

    public static void Login(UsernamePasswordToken token) {
        subject.login(token);
    }

    public static Session GetSession() {
        return subject.getSession();
    }

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
     * 获取用户信息；
     *
     * @return
     */
    public static String GetUserInfo() {
        return (String) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取用户信息并解析
     *
     * @param certification
     * @param type
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T GetUserInfoByBean(UserInfoCertification certification, Class<T> type) throws Exception {
        if (type == null)
            throw new Exception();
        String userJosn = SecurityUtils
                .getSubject()
                .getSession()
                .getAttribute("session_login_user")
                .toString();
        String userinfoStr = certification.invoke(JSONObject.parseObject(userJosn));
        return JSON.parseObject(userinfoStr, type);
    }


}
