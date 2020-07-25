package com.jhome.modules.sys.cert;

import com.domain.common.UserInfo;
import com.netflix.loadbalancer.Server;
import com.shiro.common.realm.ServerBaseAuthorizingRealm;
import com.shiro.common.token.jhomeToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * 后台登录数据Realm
 *
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
public class CustomRealm extends ServerBaseAuthorizingRealm {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof jhomeToken;
    }

    @Override
    protected SimpleAuthenticationInfo Verification(jhomeToken token) {
        try {
            String username= (String) token.getPrincipal();
            username = token.getUsername();
            String deviceType=token.getDeviceType();
            //业务认证....
            String pws = new String(((char[]) token.getCredentials()));
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(UUID.randomUUID().toString());
            userInfo.setLoginName(username);
            userInfo.setPassword("000000");
            return new SimpleAuthenticationInfo(userInfo.toString(), userInfo.getPassword(), this.getName());
        } catch (Exception ex) {
            logger.info("认证报错：%s",ex.getMessage());
        }
        return null;
    }

    @Override
    protected SimpleAuthorizationInfo GetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
