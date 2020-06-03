package com.jhome.common.shiro.realm;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authc.UsernamePasswordToken;

@Getter
@Setter
public class jhomeToken extends UsernamePasswordToken {

    /**
     * 记录设备类型 用于区分APP或者PC端用户
     */
    private String deviceType;

    /**
     * 记录登录类型 用于区分正常手机号码登录还是第三方应用登录
     */
    private Integer fromType;

    /**
     * 单点登陆
     */
    private String ssoToken;

    /**
     * @param userName
     * @param password
     * @param fromType
     * @param deviceType 登录类型
     */
    public jhomeToken(final String userName, final String password, Integer fromType, String deviceType) {
        super(userName, password);
        this.setDeviceType(deviceType);
        this.setFromType(fromType);
    }
    public jhomeToken() {
    }
}
