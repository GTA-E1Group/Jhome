package com.jhome.modules.sys.cert;

import com.domain.common.UserInfo;
import com.netflix.loadbalancer.Server;
import com.shiro.common.realm.ServerBaseAuthorizingRealm;
import com.shiro.common.token.jhomeToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.UUID;

/**
 * 后台登录数据Realm
 *
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
public class CustomRealm extends ServerBaseAuthorizingRealm {
    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof jhomeToken;
    }

    @Override
    protected SimpleAuthenticationInfo Verification(jhomeToken token) {
        String pws = new String(((char[]) token.getCredentials()));
        String username = token.getUsername();
        UserInfo ui = new UserInfo();
        ui.setUserId(UUID.randomUUID().toString());
        ui.setLoginName(username);
        ui.setPassword("000000");
        return new SimpleAuthenticationInfo(ui, ui.getPassword(), this.getName());
    }

    @Override
    protected SimpleAuthorizationInfo GetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
