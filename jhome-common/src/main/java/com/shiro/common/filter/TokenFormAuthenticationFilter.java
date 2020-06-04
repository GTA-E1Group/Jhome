package com.shiro.common.filter;

import com.daxu.common.ToolKit.StringUtil;
import com.shiro.common.client.ClientSessionDAO;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Map;

/**
 * @program: jhome-root
 * @description
 * @author: Daxv
 * @create: 2020-06-04 22:22
 **/
public class TokenFormAuthenticationFilter extends ClientFormAuthenticationFilter {
    private ClientSessionDAO cDao;

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
            cDao.setSsoToken(token);
            try {
                //执行验证 Token 通过Token 解析，拿到SessionID  调用远程回话获取session 分配session给当前系统
                subject.getSession();
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

}
