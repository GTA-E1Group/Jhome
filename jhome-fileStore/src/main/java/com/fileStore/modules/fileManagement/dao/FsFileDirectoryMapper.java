package com.fileStore.modules.fileManagement.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fileStore.modules.fileManagement.model.bo.FsFileDirectory;
import com.fileStore.modules.fileManagement.model.query.FsFileDirectoryQuery;
import org.apache.ibatis.annotations.Param;


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
 * @description:  Mapper 接口
 * @author:  1
 * @create: 2020-09-04
 **/
public interface FsFileDirectoryMapper extends BaseMapper<FsFileDirectory> {
    IPage<FsFileDirectory> selectFsFileDirectoryPageList(IPage<FsFileDirectory> page, @Param("queryObject") FsFileDirectoryQuery queryObject);


}
