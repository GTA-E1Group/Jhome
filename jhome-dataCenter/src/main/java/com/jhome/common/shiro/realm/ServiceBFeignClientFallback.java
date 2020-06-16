package com.jhome.common.shiro.realm;

import com.daxu.common.Bus.RequestResult;
import com.daxu.common.Bus.ResponResult;
import com.domain.common.PermissionContext;
import com.shiro.common.session.ShiroSession;

public class ServiceBFeignClientFallback implements UserRemoteServiceInterface {
    @Override
    public String getSession(String sessionId) {
        return null;
    }

    @Override
    public ResponResult createSession(ShiroSession session) {
        return null;
    }

    @Override
    public String updateSession(String sessionJson) {
        return null;
    }
    @Override
    public boolean deleteSession(RequestResult result) {
        return false;
    }

    @Override
    public PermissionContext getPermissions(String username) {
        return null;
    }


}
