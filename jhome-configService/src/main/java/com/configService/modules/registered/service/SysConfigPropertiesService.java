package com.configService.modules.registered.service;

import com.configService.modules.registered.model.po.SysConfigProperties;
import com.configService.modules.registered.model.qo.SysConfigPropertiesQuery;
import com.bracket.common.Bus.ResponseJson;

import java.util.List;

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
 * @description:
 * @author: Daxv
 * @create: 2020-08-08 12:39
 **/
public interface SysConfigPropertiesService {
    /**
     * 保存用户
     * @param user
     */
    boolean save(SysConfigProperties user);


    /**
     * 修改
     * @param user
     */
    boolean update(SysConfigProperties user);

    /**
     * 删除
     * @param id
     */
    boolean delete(String id);

    /**
     * 查询全部配置
     * @return
     */
    List<SysConfigProperties> findAll();


    /**
     * 根据条件查询
     * @param
     * @return
     */
    ResponseJson getList(SysConfigPropertiesQuery sysConfigPropertiesQuery);

    /**
     * 批量更新
     * @param sysConfigPropertiesList
     * @return
     */
    boolean batchUpdate(List<SysConfigProperties> sysConfigPropertiesList);
}
