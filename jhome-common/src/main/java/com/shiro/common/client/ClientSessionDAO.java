package com.shiro.common.client;

import com.alibaba.fastjson.JSON;
import com.daxu.common.Bus.RequestResult;
import com.daxu.common.Bus.ResponResult;
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
public class ClientSessionDAO extends CachingSessionDAO {

    private RemoteBaseInterface remoteService;

    private String appKey;

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Override
    protected Serializable doCreate(Session session) {
        ShiroSession shiroSession=null;
        Serializable sessionId;
        /*执行模板方法，如果是单点登陆过来的直接获取缓存中的session，无需在创建*/
        shiroSession= (ShiroSession) this.doGetSSoBySession();
        //判断是否有单点登陆认证过来的Session
        if(shiroSession!=null)
        {
            sessionId=shiroSession.getId();
            session=shiroSession;
        }else {
            //远程生成
            ResponResult responResult = remoteService.createSession((ShiroSession) session);
            sessionId = (Serializable) responResult.getData();
        }
        assignSessionId(session, sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        String sessionId = (String) serializable;
        String shiroSessionJson = remoteService.getSession("", sessionId);
        ShiroSession shiroSession = JSON.parseObject(shiroSessionJson, ShiroSession.class);
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

    /**
     * 分配Session
     * @return
     */
    protected Session doGetSSoBySession() {
        return null;
    }

    public RemoteBaseInterface getRemoteService() {
        return remoteService;
    }

    public void setRemoteService(RemoteBaseInterface remoteService) {
        this.remoteService = remoteService;
    }

}
