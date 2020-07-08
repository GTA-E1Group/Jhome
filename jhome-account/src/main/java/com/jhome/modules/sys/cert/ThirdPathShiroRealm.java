package com.jhome.modules.sys.cert;

import com.jhome.common.shiro.realm.BaseAuthorizingRealm;
import com.shiro.common.token.jhomeToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;


/**
 * 第三方认证
 *
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
public class ThirdPathShiroRealm extends BaseAuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof jhomeToken;
    }
    @Override
    protected SimpleAuthenticationInfo Verification(jhomeToken token) {
        return null;
    }
}
