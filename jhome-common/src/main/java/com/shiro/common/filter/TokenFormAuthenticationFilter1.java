package com.shiro.common.filter;

import com.daxu.common.ToolKit.StringUtil;
import com.shiro.common.client.ClientSessionDAO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.apache.shiro.web.subject.WebSubject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Map;

/**
 * @program: jhome-root
 * @description
 * @author: Daxv
 * @create: 2020-06-04 22:22
 **/
public class TokenFormAuthenticationFilter1 extends AbstractShiroFilter {
    private ClientSessionDAO cDao;

    public ClientSessionDAO getCDao() {
        return cDao;
    }

    public void setCDao(ClientSessionDAO cDao) {
        this.cDao = cDao;
    }

    @Override
    protected WebSubject createSubject(ServletRequest request, ServletResponse response) {
        //return super.createSubject(request, response);
//        WebSubject.Builder builder=  (new WebSubject.Builder(this.getSecurityManager(), request, response));
//        SubjectContext context = securityManager.createSubject();
//        context.setAuthenticated(true);
//        context.setAuthenticationToken(token);
//        context.setAuthenticationInfo(info);
//        context.setSecurityManager(securityManager);
//        builder.buildWebSubject()
        return (new WebSubject.Builder(this.getSecurityManager(), request, response)).buildWebSubject();
        //return this.securityManager.createSubject(this.subjectContext);
    }
}
