package com.configService.modules.registered.web;

import com.configService.modules.registered.model.po.SysConfigProperties;
import com.configService.modules.registered.model.qo.SysConfigPropertiesQuery;
import com.configService.modules.registered.service.SysConfigPropertiesService;
import com.configService.modules.registered.web.baseController.BaseController;
import com.daxu.common.Bus.ResponseJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
 * @description: 注册控制器
 * @author: Daxv
 * @create: 2020-08-08 12:35
 **/
@Api(tags = "z、注册系统")
@RestController
@RequestMapping("/register")

public class RegisterController extends BaseController {
    @Autowired
    public SysConfigPropertiesService sysConfigPropertieService;

    @ApiOperation(value = "获取注册列表参数", notes = "是否是公共组件填写：  0:不是 1:是")
    @RequestMapping(value = "/getRegisterList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getRegisterList(SysConfigPropertiesQuery sysConfigPropertiesQuery) {
        return sysConfigPropertieService.getList(sysConfigPropertiesQuery).toString();
    }

    @ApiOperation(value = "保存参数")
    @ResponseBody
    @RequestMapping(value = "/saveRegisterInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseJson saveRegisterInfo(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @Validated @RequestBody List<SysConfigProperties> sysConfigProperties,
                                         BindingResult result) {
        if (result.hasErrors())
            return new ResponseJson().error(result.getFieldError().getField());
        try {
            sysConfigPropertieService.batchUpdate(sysConfigProperties);
        } catch (Exception ex) {
            return new ResponseJson().error(ex.getMessage());
        }
        return new ResponseJson().success();
    }
}
