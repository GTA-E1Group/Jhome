package com.jhome.modules.sys.cert;

import com.jhome.common.shiro.realm.BaseAuthorizingRealm;
import com.jhome.modules.sys.model.po.UserInfo;
import com.shiro.common.token.jhomeToken;
import org.apache.shiro.authc.*;

import java.util.UUID;

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

    @Override
    protected SimpleAuthenticationInfo Verification(jhomeToken token) {
        String pws=new String(((char[])token.getCredentials()));
        String username=token.getUsername();
        UserInfo ui=new UserInfo();
        ui.setUserId(UUID.randomUUID().toString());
        ui.setAccount("daxu");
        ui.setPassword("000000");
        return new SimpleAuthenticationInfo(ui, ui.getPassword(), this.getName());
    }
}
