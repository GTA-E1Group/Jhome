package com.shiro.common.client;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import java.io.Serializable;

/**
 * @program: jhome-root
 * @description
 * @author: Daxv
 * @create: 2020-06-03 22:50
 **/
public  class TokenBySsoAuthorizing  extends ClientSessionDAO {
    private String ssoToken;
    @Override
    protected Session doGetSSoBySession() {
        //解析ssoToken
        String sessionId="";
        if(!ssoToken.isEmpty())
        {
            //解析Tonken
            sessionId=this.getSsoToken();
            //根据ssToken获取Session
            Session session=  super.doReadSession(sessionId);
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
}
