package com.jhome.common.shiro.realm;

import com.jhome.modules.sys.pojo.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.util.List;
import java.util.UUID;

/**
 * 系统安全认证实现类
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
public abstract class BaseAuthorizingRealm extends AuthorizingRealm {
    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //设备判断
        Subject subject = SecurityUtils.getSubject();
        String deviceType = (String) subject.getSession().getAttribute(
                SessionCons.DEVICE_TYPE);
        if (deviceType.equals(DeviceType.APP.toString())) {//true则往下执行

        }
        System.out.println("授权");
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("CustomRealmCustomRealmCustomRealmCustomRealmCustomRealmCustomRealmCustomRealm");
        // 1.向下转型
        jhomeToken upToken = (jhomeToken) authenticationToken;
        // TODO Auto-generated method stub

        //测试代码
        if(1==1)
        {
            UserInfo ui=new UserInfo();
            ui.setUserId(UUID.randomUUID().toString());
            ui.setAccount("daxu");
            ui.setPassword("000000");
            return new SimpleAuthenticationInfo(ui, ui.getPassword(), this.getName());
        }

        System.out.print("----------------认证---------------");
        String userName = (String) upToken.getPrincipal();
        String pws=(String)upToken.getCredentials();
        List<UserInfo> userInfos =null;
        if (userInfos != null && userInfos.size() > 0) {
            UserInfo userInfo = userInfos.get(0);
            return new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), this.getName());
        }
        //System.out.println("认证");
        //Object obj=  authesnticationToken.getPrincipal();
        //UserInfo userEntity =(UserInfo)  AuthUtil.getUserInfo(request);
        //AuthUtil.userLogin(String.valueOf(user.getId()),user, response);
        return null;
    }


}
