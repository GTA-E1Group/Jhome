package com.shiro.common.client;

import com.alibaba.fastjson.JSON;
import com.daxu.common.Bus.RequestResult;
import com.daxu.common.Bus.ResponResult;
import com.daxu.common.Identity.UserUtil;
import com.daxu.common.ToolKit.StringUtil;
import com.shiro.common.SessionDaoZH;
import com.shiro.common.session.ShiroSession;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;

/**
 * 关键一步实现调用服务器的RedisSessionDao 实现对统一数据源操作
 *
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
public class ClientSessionDAO extends TokenBySsoAuthorizing {
    private String ssoToken;

    @Override
    protected Session doGetSSoBySession() {
        //解析ssoToken
        String sessionId = "";
        if (StringUtil.isNotBlank(ssoToken)) {
            //解析Tonken
            sessionId = (String) UserUtil.ParsingToken(this.getSsoToken());
            //根据ssToken获取Session
            Session session = super.doReadSession(sessionId);
            return session;
        }
        return null;
    }

    public String getSsoToken() {
        return ssoToken;
    }

    public void setSsoToken(String ssoToken) {
        this.ssoToken = ssoToken;
    }

    @Override
    public void setRemoteService(RemoteBaseInterface remoteService) {
        super.setRemoteService(remoteService);
    }


}
