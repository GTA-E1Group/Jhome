package com.jhome.modules.sys.web;/**
 * @program: jhome-root
 * @description
 * @author: Daxv
 * @create: 2020-07-18 16:10
 **/

import com.daxu.common.Identity.UserUtil;
import com.daxu.common.ToolKit.JSONUtils;
import com.jhome.modules.sys.web.baseController.baseController;
import com.shiro.common.realm.SessionCons;
import com.shiro.common.token.DeviceType;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 //
 //                       .::::.
 //                     .::::::::.
 //                    :::::::::::
 //                 ..:::::::::::'
 //              '::::::::::::'
 //                .::::::::::
 //           '::::::::::::::..
 //                ..::::::::::::.
 //              ``::::::::::::::::
 //               ::::``:::::::::'        .:::.
 //              ::::'   ':::::'       .::::::::.
 //            .::::'      ::::     .:::::::'::::.
 //           .:::'       :::::  .:::::::::' ':::::.
 //          .::'        :::::.:::::::::'      ':::::.
 //         .::'         ::::::::::::::'         ``::::.
 //     ...:::           ::::::::::::'              ``::.
 //    ```` ':.          ':::::::::'                  ::::..
 //                       '.:::::'                    ':'````..
 * @program: jhome-root
 * @description: CAS单点登录回调
 * @author: Daxv
 * @create: 2020-07-18 16:10
 **/
@RestController
@RequestMapping("/${adminPath}")
public class CasSinglePointController extends baseController {
    @ApiOperation(value = "CAS - 单点登陆接口回调", notes = "单点登陆接口回调")
    @RequestMapping(value = {"/", "", "/index"}, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public void smartCampusLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
//            Principal principal = request.getUserPrincipal();
//            Object principal2 = UserUtil.getSubject().getPrincipal();
//            String dd = request.getRemoteUser();
            /* 返回本地token  */
            if (UserUtil.getSubject().isAuthenticated()) {

                String userJson = JSONUtils.beanToJson(UserUtil.getSubject().getPrincipal());
                Session session = UserUtil.getSubject().getSession();
                session.setAttribute(SessionCons.DEVICE_TYPE, DeviceType.SmartCampusCas.toString());
                session.setAttribute(SessionCons.LOGIN_USER_SESSION, userJson);
                response.sendRedirect("http://10.1.19.61:8080/#/sslogin?token=" + session.getId());
            }
        } catch (Exception ex) {
            logger.debug(String.format("智慧校园回调函数报错：%s",ex.getMessage()));
        }

    }
}
