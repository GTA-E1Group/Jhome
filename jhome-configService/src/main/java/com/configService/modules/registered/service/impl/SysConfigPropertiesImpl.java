package com.configService.modules.registered.service.impl;

import com.configService.modules.registered.dao.SysConfigPropertiesDao;
import com.configService.modules.registered.model.po.SysConfigProperties;
import com.configService.modules.registered.model.qo.SysConfigPropertiesQuery;
import com.configService.modules.registered.service.SysConfigPropertiesService;
import com.daxu.common.Bus.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
 * @create: 2020-08-08 13:10
 **/
@Service
public class SysConfigPropertiesImpl implements SysConfigPropertiesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysConfigPropertiesImpl.class);
    @Autowired
    protected SysConfigPropertiesDao sysConfigPropertiesDao;

    @Override
    public boolean save(SysConfigProperties sysConfigProperties) {
        return sysConfigPropertiesDao.save(sysConfigProperties);
    }

    @Override
    public boolean update(SysConfigProperties sysConfigProperties) {
        return sysConfigPropertiesDao.update(sysConfigProperties);
    }

    @Override
    public boolean delete(String id) {
        return sysConfigPropertiesDao.delete(id);
    }

    @Override
    public List<SysConfigProperties> findAll() {
        return sysConfigPropertiesDao.findAll();
    }

    @Override
    public ResponseJson getList(SysConfigPropertiesQuery sysConfigPropertiesQuery) {
        ResponseJson responseJson = new ResponseJson();
        List<ResponseJson> list = new ArrayList();
        List<SysConfigProperties> sysConfigProperties = sysConfigPropertiesDao.getList(sysConfigPropertiesQuery);
        if (sysConfigProperties == null)
            return null;
        responseJson.success();
        sysConfigProperties.forEach(c -> {
            ResponseJson responseJsonChild = new ResponseJson();
            String[] item = c.getParamName().split("-");
            if (list.stream().filter(t -> t.get("title").equals(item[0])).findFirst().isPresent())
                return;
            responseJsonChild.setValue("title", item[0]);
            List<SysConfigProperties> childList = sysConfigProperties.stream().filter(t -> t.getParamName().contains(item[0])).collect(Collectors.toList());
            responseJsonChild.setValue("formdata", childList);
            list.add(responseJsonChild);
        });
        responseJson.setValue("data", list);
        return responseJson;
    }

    @Transactional
    public boolean batchUpdate(List<SysConfigProperties> sysConfigPropertiesList) {
        try {
            sysConfigPropertiesList.stream().forEach(e -> {
                try {
                    this.update(e);
                } catch (Exception ex) {
                    LOGGER.info(String.format("批量插入报错：%s", ex.getMessage()));
                }
            });
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
