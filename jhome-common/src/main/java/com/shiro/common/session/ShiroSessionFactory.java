package com.shiro.common.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
/**
 * 继承SimpleSession并重写让相关的字段可以被序列化(不被transient修饰)重写设置自定义的session生产会话对象sessionManager.setSessionFactory(sessionFactory())
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
public class ShiroSessionFactory implements SessionFactory {
    @Override
    public Session createSession(SessionContext sessionContext) {
        if(sessionContext != null) {
            String host = sessionContext.getHost();
            if(host != null) {
                return new ShiroSession(host);
            }
        }
        return new ShiroSession();
    }
}
