package com.jhome.common.shiro.realm;

import org.apache.shiro.authc.*;
/**
 * 移动端 认证
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
public class AppShiroRealm extends BaseAuthorizingRealm {
    @Override
    public boolean supports(AuthenticationToken token){
        return token != null && token instanceof jhomeToken;
    }
}
