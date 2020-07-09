package com.shiro.common.filter;

import com.alibaba.fastjson.JSON;
import com.daxu.common.Identity.UserUtil;
import com.daxu.common.ToolKit.CookieUtil;
import com.daxu.common.ToolKit.StringUtil;
import com.shiro.common.session.ClientSessionDAO;
import com.shiro.common.session.RemoteBaseInterface;
import com.shiro.common.session.ShiroSession;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: jhome-root
 * @description
 * @author: Daxv
 * @create: 2020-06-04 22:22
 **/
public class ClientTokenFormAuthenticationFilter extends ClientFormAuthenticationFilter {
    private ClientSessionDAO cDao;
    private RemoteBaseInterface remoteService;

    /**
     * 单点登录拦截
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        //获取URL参数上的Token
        String token = this.GetToken(request, response);
        //只允许单点登录进入
        //跨浏览器，跨电脑访问，需要根据第一次Tokne 重新绘制本地session
        if (StringUtil.isNotBlank(token) && !subject.isAuthenticated()) {
            try {
                cDao.setSsoToken(token);
                String sessionId = (String) UserUtil.ParsingToken(token);
                ShiroSession shiroSession = JSON.parseObject(remoteService.getSession(sessionId), ShiroSession.class);
                if (shiroSession == null) {
                    return false;
                }
                subject.getSession();
                /*
                subject.logout();
                subject = SecurityUtils.getSubject();
               executeChain(request,response,null);
                //判断token是否有效
                //解析Tonken
                //执行验证 Token 通过Token 解析，拿到SessionID  调用远程回话获取session 分配session给当前系统
                DefaultWebSecurityManager securityManager= (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
                SubjectContext context = securityManager.createSubject();
                context.setAuthenticated(true);
                context.setAuthenticationToken(token);
                context.setAuthenticationInfo(info);
                context.setSecurityManager(securityManager);
                securityManager.createSubject(context);*/
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
        return subject.isAuthenticated();
    }

    /**
     * @Description 读取token
     * @Author daxv
     * @Date
     * @Remarks ...
     */

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


    public ClientSessionDAO getCDao() {
        return cDao;
    }

    public void setCDao(ClientSessionDAO cDao) {
        this.cDao = cDao;
    }

    public void setRemoteService(RemoteBaseInterface remoteService) {
        this.remoteService = remoteService;
    }
}
