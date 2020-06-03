package com.shiro.common.client;

import com.alibaba.fastjson.JSON;
import com.daxu.common.Bus.RequestResult;
import com.daxu.common.Bus.ResponResult;
import com.shiro.common.SessionDaoZH;
import com.shiro.common.session.ShiroSession;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;

/**
 * 关键一步实现调用服务器的RedisSessionDao 实现对统一数据源操作
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
public class ClientSessionDAO extends CachingSessionDAO {
    private RemoteBaseInterface remoteService;
    public void setRemoteService(RemoteBaseInterface remoteService) {
        this.remoteService = remoteService;
    }
    private String appKey;
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Override
    protected Serializable doCreate(Session session) {
        ShiroSession shiroSession = (ShiroSession) session;
        ResponResult responResult = remoteService.createSession((ShiroSession) session);
        Serializable sessionId = (Serializable) responResult.getData();
        assignSessionId(session, sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        String sessionId = (String) serializable;
        String shiroSessionJson=remoteService.getSession("", sessionId);
        ShiroSession shiroSession =JSON.parseObject(shiroSessionJson,ShiroSession.class);
        System.out.println("12312");
        //return  shiroSession;
        return SessionDaoZH.SerializedStringToAttributeBean(shiroSession);
    }


    @Override
    protected void doUpdate(Session session) {
        if (session instanceof SimpleSession) {
            ShiroSession shiroSession = (ShiroSession) session;
            if (!shiroSession.isChanged()) {
                return;
            }
            remoteService.updateSession("", shiroSession);
        }
    }


    @Override
    protected void doDelete(Session session) {
        RequestResult result = new RequestResult();
        result.setData(session);
        remoteService.deleteSession("", result);
    }
    @Override
    public Collection<Session> getActiveSessions() {
        return null;
    }

}
