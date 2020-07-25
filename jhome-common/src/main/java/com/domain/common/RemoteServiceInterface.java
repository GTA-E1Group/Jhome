package com.domain.common;

import org.apache.shiro.session.Session;

import java.io.Serializable;

public interface RemoteServiceInterface {
    public Session getSession(Serializable sessionId);

    Serializable createSession(Session session);

    public void updateSession(Session session);

    public void deleteSession(Session session);

    public PermissionContext getPermissions(String username);
}
