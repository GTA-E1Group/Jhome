package com.jhome.common.shiro.realm;

import com.daxu.common.ToolKit.CookieUtil;
import com.daxu.common.ToolKit.StringUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
public class JhomeDefaultWebSessionManager extends DefaultWebSessionManager {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    public final static String TOKEN_NAME = "JhomeToken";

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        if (sessionId == null) {
            //slogger.debug("Unable to resolve session ID from SessionKey [{}].  Returning null to indicate a "
            //   + "session could not be found.", sessionKey);
            return null;
        }
        // *************** 解决频繁从远程获取session信息 ****************
        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }
        if (request != null) {
            Object s = request.getAttribute(sessionId.toString());
            if (s != null) {
                //logger.debug("SESSION FROM LOCAL REQUEST OBJECT");
                return (Session) s;
            }
        }
        // *************** END ****************
        Session s = retrieveSessionFromDataSource(sessionId);
        if (s == null) {
            // session ID was provided, meaning one is expected to be found, but
            // we couldn't find one:
            String msg = "Could not find session with ID [" + sessionId + "]";
            throw new UnknownSessionException(msg);
        }
        //远程获取并赋值到request对象中
        if (request != null) {
            request.setAttribute(sessionId.toString(), s);
        }
        return s;
    }

    /**
     * @Description 读取token
     * @Author daxv
     * @Date
     * @Remarks ...
     */
    @Override
    public Serializable getSessionId(SessionKey key) {
        Serializable sessionId = key.getSessionId();
        if (sessionId == null) {
            HttpServletRequest request = WebUtils.getHttpRequest(key);
            HttpServletResponse response = WebUtils.getHttpResponse(key);
            sessionId = this.getSessionId(request, response);
            if (sessionId == null)
                return null;
        }
        HttpServletRequest request = WebUtils.getHttpRequest(key);
        request.setAttribute(TOKEN_NAME, sessionId.toString());
        return sessionId;
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = this.GetToken(request, response);
        if (token == null) {
            //token= SessionCons.TOKEN_PREFIX+ UUID.randomUUID().toString();
        }
        httpServletRequest.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
        httpServletRequest.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
        httpServletRequest.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        httpServletRequest.setAttribute(ShiroHttpServletRequest.SESSION_ID_URL_REWRITING_ENABLED, isSessionIdUrlRewritingEnabled());
        return token;
    }

    public String GetToken(ServletRequest request, ServletResponse response) {
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

}
