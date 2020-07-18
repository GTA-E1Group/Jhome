package com.jhome.common.shiro.filter;/**
 * @program: jhome-root
 * @description
 * @author: Daxv
 * @create: 2020-07-18 15:09
 **/

import com.daxu.common.Bus.ResponseJson;
import com.daxu.common.Http.HttpUtil;
import com.daxu.common.ToolKit.CookieUtil;
import com.daxu.common.ToolKit.StringUtil;
import com.shiro.common.token.jhomeToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 //
 //                       .::::.
 //                     .::::::::.
 //                    :::::::::::
 //                 ..:::::::::::'
 //              '::::::::::::'
 //                .::::::::::
 //           '::::::::::::::..
 //                ..::::::::::::.
 //              ``::::::::::::::::
 //               ::::``:::::::::'        .:::.
 //              ::::'   ':::::'       .::::::::.
 //            .::::'      ::::     .:::::::'::::.
 //           .:::'       :::::  .:::::::::' ':::::.
 //          .::'        :::::.:::::::::'      ':::::.
 //         .::'         ::::::::::::::'         ``::::.
 //     ...:::           ::::::::::::'              ``::.
 //    ```` ':.          ':::::::::'                  ::::..
 //                       '.:::::'                    ':'````..
 * @program: jhome-root
 * @description: 前后台分离模式过滤器
 * @author: Daxv
 * @create: 2020-07-18 15:09
 **/
public class SeparationModeFromAuthenticationFilter extends MixedModeFormAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(SeparationModeFromAuthenticationFilter.class);

    /**
     * 跨域处理 （对前后端非分离模式支持）
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);

        //url 传递参数
        String sessionId = httpServletRequest.getParameter("sessionId");
        if (StringUtil.isNotBlank(sessionId)) {
            //sessionId = (String) UserUtil.ParsingToken(sessionId);
            CookieUtil.set(httpServletResponse, "LuxCookie", sessionId, 30);
        }
        if ("OPTIONS".equals(httpServletRequest.getMethod())){
            httpServletResponse.setStatus(org.apache.http.HttpStatus.SC_NO_CONTENT);
            logger.info("OPTIONS 放行");
            return true;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        try {
            Subject subject = SecurityUtils.getSubject();
            boolean bo = subject.isAuthenticated();
            if (!bo) {
                if (isLoginRequest(request, response)) {
                    if (isLoginSubmission(request, response)) {
                        return executeLogin(request, response);
                    } else {
                        return true;
                    }
                } else {
                    HttpUtil.SendFlush(response, new ResponseJson().error("登陆已经失效，请重新登陆！~"));
                }
            }
        }catch (Exception ex)
        {
            HttpUtil.SendFlush(response, new ResponseJson().error("登陆已经失效，请重新登陆！~"));
        }
        return false;
    }
    /**
     * 成功转发
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
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        return new jhomeToken();
    }
}
