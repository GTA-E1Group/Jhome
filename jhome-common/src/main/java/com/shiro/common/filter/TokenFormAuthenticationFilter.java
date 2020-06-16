package com.shiro.common.filter;

import com.alibaba.fastjson.JSON;
import com.daxu.common.Identity.UserUtil;
import com.daxu.common.ToolKit.StringUtil;
import com.shiro.common.client.ClientSessionDAO;
import com.shiro.common.client.RemoteBaseInterface;
import com.shiro.common.session.ShiroSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @program: jhome-root
 * @description
 * @author: Daxv
 * @create: 2020-06-04 22:22
 **/
public class TokenFormAuthenticationFilter extends ClientFormAuthenticationFilter {
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
        String token = "";
        Map<String, String[]> params = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            token = entry.getValue()[0].toString();
            break;
        }
        //只允许单点登录进入
        if (StringUtil.isNotBlank(token) && !subject.isAuthenticated()) {
            try {
                cDao.setSsoToken(token);
                String sessionId = (String) UserUtil.ParsingToken(token);
                ShiroSession shiroSession = JSON.parseObject(remoteService.getSession(sessionId), ShiroSession.class);
                if (shiroSession==null) {
                    return false;
                }
                Session session = subject.getSession();
                //
                //subject.logout();
                //subject = SecurityUtils.getSubject();
//               executeChain(request,response,null);
                //判断token是否有效
                //解析Tonken

                //执行验证 Token 通过Token 解析，拿到SessionID  调用远程回话获取session 分配session给当前系统
                //DefaultWebSecurityManager securityManager= (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
//                SubjectContext context = securityManager.createSubject();
//                context.setAuthenticated(true);
//                context.setAuthenticationToken(token);
//                context.setAuthenticationInfo(info);
//                context.setSecurityManager(securityManager);
//                securityManager.createSubject(context);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
        return subject.isAuthenticated();
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
