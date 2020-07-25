package com.jhome.common.utils;/**
 * @program: jhome-root
 * @description
 * @author: Daxv
 * @create: 2020-07-25 15:53
 **/

import com.daxu.common.Identity.UserUtil;
import com.daxu.common.ToolKit.StringUtil;
import com.domain.common.UserInfo;
import com.shiro.common.realm.ServerRedisSessionDao;
import jdk.nashorn.internal.parser.Token;

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
 * @description: 用户辅助类
 * @author: Daxv
 * @create: 2020-07-25 15:53
 **/
public class UserAuxiliary extends UserUtil {
    private static ServerRedisSessionDao sessionDao;
    private static UserInfo userInfo;

    public UserAuxiliary() {
    }

    public static UserInfo getUserInfo(String Token) {
        if (userInfo == null)
            userInfo = new UserInfo();
        userInfo = GetUserInfo(c -> {
            if (StringUtil.isNotBlank(Token))
                c = Token;
            return sessionDao.readSession(c);
        });
        return userInfo;
    }

    public ServerRedisSessionDao getSessionDao() {
        return sessionDao;
    }

    public void setSessionDao(ServerRedisSessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }
}
