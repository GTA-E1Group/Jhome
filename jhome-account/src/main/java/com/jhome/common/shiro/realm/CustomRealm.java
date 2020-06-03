package com.jhome.common.shiro.realm;

import com.daxu.common.Identity.AuthUtil;
import com.jhome.modules.sys.pojo.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.util.List;

/**
 * 后台登录数据Realm
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
public class CustomRealm extends BaseAuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token){
        return token != null && token instanceof jhomeToken;
    }
}
