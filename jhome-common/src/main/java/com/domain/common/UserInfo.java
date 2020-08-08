package com.domain.common;/**
 * @program: jhome-root
 * @description
 * @author: Daxv
 * @create: 2020-07-09 23:36
 **/

import com.alibaba.fastjson.JSONObject;
import com.bracket.common.ToolKit.JSONUtils;
import jdk.nashorn.internal.parser.JSONParser;

/**
 * //
 * //                       .::::.
 * //                     .::::::::.
 * //                    :::::::::::
 * //                 ..:::::::::::'
 * //              '::::::::::::'
 * //                .::::::::::
 * //           '::::::::::::::..
 * //                ..::::::::::::.
 * //              ``::::::::::::::::
 * //               ::::``:::::::::'        .:::.
 * //              ::::'   ':::::'       .::::::::.
 * //            .::::'      ::::     .:::::::'::::.
 * //           .:::'       :::::  .:::::::::' ':::::.
 * //          .::'        :::::.:::::::::'      ':::::.
 * //         .::'         ::::::::::::::'         ``::::.
 * //     ...:::           ::::::::::::'              ``::.
 * //    ```` ':.          ':::::::::'                  ::::..
 * //                       '.:::::'                    ':'````..
 *
 * @program: jhome-root
 * @description: 用户信息
 * @author: Daxv
 * @create: 2020-07-09 23:36
 **/
public class UserInfo extends DefaultUserInfo {
    private String remotelyToken;//远程token


    @Override
    public String toString() {
        return JSONUtils.beanToJson(this);
    }

    public String getRemotelyToken() {
        return remotelyToken;
    }

    public void setRemotelyToken(String remotelyToken) {
        this.remotelyToken = remotelyToken;
    }
}
