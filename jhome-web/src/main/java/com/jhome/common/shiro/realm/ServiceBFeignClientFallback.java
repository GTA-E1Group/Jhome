package com.jhome.common.shiro.realm;

import com.daxu.common.Bus.RequestResult;
import com.daxu.common.Bus.ResponResult;
import com.domain.common.PermissionContext;
import com.shiro.common.session.ShiroSession;

public class ServiceBFeignClientFallback implements UserRemoteServiceInterface {


    @Override
    public String getSession(String appKey, String sessionId) {
        return null;
    }

    @Override
    public ResponResult createSession(ShiroSession session) {
        return null;
    }

    @Override
    public void updateSession(String appKey, ShiroSession session) {

    }

    @Override
    public boolean deleteSession(String appKey, RequestResult result) {
        return false;
    }

    @Override
    public PermissionContext getPermissions(String appKey, String username) {
        return null;
    }


}
