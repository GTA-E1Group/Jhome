package com.jhome.modules.sys.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户信息类
 */
@Getter
@Setter
public class UserInfo extends DataEntity<UserInfo> {
    private String userId;
    private String account;
    private String password;
}
