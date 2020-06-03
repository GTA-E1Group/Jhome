package com.shiro.common.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * ClientAuthenticationFilter是用于实现身份认证的拦截器（authc），当用户没有身份认证时；
 * 1、首先得到请求参数backUrl，即登录成功重定向到的地址；
 * 2、然后保存保存请求到会话，并重定向到登录地址（server模块）；
 * 3、登录成功后，返回地址按照如下顺序获取：backUrl、保存的当前请求地址、defaultBackUrl（即设置的successUrl）
 */
public class ClientFormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(ClientFormAuthenticationFilter.class);

    //允许访问
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        logger.info("ClientAuthenticationFilter----处理拦截URL："+httpServletRequest.getRequestURI());
        Subject subject = getSubject(request, response);
        //Subject subject = SecurityUtils.getSubject();
        return subject.isAuthenticated();
    }
    //拒绝访问
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String backUrl = request.getParameter("backUrl");
        saveRequest(request, backUrl, getDefaultBackUrl(WebUtils.toHttp(request)));
        redirectToLogin(request, response);
        return false;
    }
    // 调用远程会话 将返回地址 存储到 Redis中
    protected void saveRequest(ServletRequest request, String backUrl, String fallbackUrl) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        session.setAttribute("authc.fallbackUrl", fallbackUrl);
        SavedRequest savedRequest = new ClientSavedRequest(httpRequest, backUrl);

        session.setAttribute(WebUtils.SAVED_REQUEST_KEY, savedRequest);
    }
    //获取登录成功后返回地址
    private String getDefaultBackUrl(HttpServletRequest request) {
        String scheme = request.getScheme();
        String domain = request.getServerName();
        int port = request.getServerPort();
        String contextPath = request.getContextPath();
        StringBuilder backUrl = new StringBuilder(scheme);
        backUrl.append("://");
        backUrl.append(domain);
        if("http".equalsIgnoreCase(scheme) && port != 80) {
            backUrl.append(":").append(String.valueOf(port));
        } else if("https".equalsIgnoreCase(scheme) && port != 443) {
            backUrl.append(":").append(String.valueOf(port));
        }
        backUrl.append(contextPath);
        backUrl.append(getSuccessUrl());
        return backUrl.toString();
    }


}
