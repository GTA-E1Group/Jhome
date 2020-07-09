package com.jhome.common;

import com.shiro.common.realm.BaseAuthorizingRealm;
import com.shiro.common.token.jhomeToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 后台登录数据Realm
 * 系统安全认证实现类
 */
public class ClientRealm extends BaseAuthorizingRealm {

    //通过RPC调用
    @Autowired
    public UserRemoteServiceInterface remoteService;
    public String appKey;

    //认证
    @Override
    protected SimpleAuthenticationInfo Verification(jhomeToken token) {
        System.out.println("永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用永远不会被调用");
        return null;
    }
    // 授权
    @Override
    protected SimpleAuthorizationInfo GetAuthorizationInfo(PrincipalCollection principalCollection) {
        //用户授权
/*        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        PermissionContext context = remoteService.getPermissions(appKey, username);
        authorizationInfo.setRoles(context.getRoles());
        authorizationInfo.setStringPermissions(context.getPermissions());*/
        return null;
    }


}
