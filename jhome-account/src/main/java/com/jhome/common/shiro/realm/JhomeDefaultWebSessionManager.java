package com.jhome.common.shiro.realm;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

/**
 *
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
public class JhomeDefaultWebSessionManager extends DefaultWebSessionManager {
    @Override
    protected Session newSessionInstance(SessionContext context) {
        return super.newSessionInstance(context);
    }
}
