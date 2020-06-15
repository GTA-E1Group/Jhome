package com.jhome.common.shiro.filter;

import com.daxu.common.ToolKit.StringUtil;
import com.jhome.common.shiro.realm.jhomeToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(FormAuthenticationFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("登录验证..................................");


        Subject subject = SecurityUtils.getSubject();
        boolean bo = subject.isAuthenticated();


        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            if (logger.isTraceEnabled()) {
                logger.trace("Attempting to access a path which requires authentication.  Forwarding to the " + "Authentication url ["
                        + getLoginUrl() + "]");
            }
            redirectToLogin(request, response); // 此过滤器优先级较高，未登录，则跳转登录页，方便 CAS 登录
            //saveRequestAndRedirectToLogin(request, response);  // 去掉保存登录前的跳转地址  ThinkGem
            return false;
        }


    }

    /**
     * 多数据源，无法确定usernametoken的转换类型 如果没有指定 deviceType 默认读取所有数据源的认证
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String userName = getUsername(request);
        String pwd = getPassword(request);
        jhomeToken jhomeToken = new jhomeToken(userName, pwd, 0, "");
        return jhomeToken;
    }

    /**
     * 登录成功调用事件
     *
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("登录成功调用事件..............");
        return true;
    }

    /**
     * 登录失败调用的方法
     *
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        System.out.println("登录失败调用的方法");
        return true;
    }


    /**
     * 多项目登录，比如如果是从其他应用中重定向过来的，
     * 首先检查Session中是否有“authc.fallbackUrl”属性，
     * 如果有就认为它是默认的重定向地址；
     * 否则使用Server自己的successUrl作为登录成功后重定向到的地址。
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        String fallbackUrl = (String) getSubject(request, response)
                .getSession().getAttribute("authc.fallbackUrl");
        if (StringUtil.isBlank(fallbackUrl)) {
            fallbackUrl = getSuccessUrl();
        }
        WebUtils.redirectToSavedRequest(request, response, fallbackUrl);
    }


}
