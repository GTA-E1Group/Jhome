package com.fileStore.modules.fileManagement.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fileStore.modules.fileManagement.model.bo.FsFileDirectoryDetail;
import com.fileStore.modules.fileManagement.model.query.FsFileDirectoryDetailQuery;
import com.fileStore.modules.fileManagement.model.vo.FsFileDirectoryDetailView;
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
public interface FsFileDirectoryDetailMapper extends BaseMapper<FsFileDirectoryDetail> {
    IPage<FsFileDirectoryDetailView> selectFsFileDirectoryDetailPageList(IPage<FsFileDirectoryDetailView> page, @Param("queryObject") FsFileDirectoryDetailQuery queryObject);
}
