package com.fileStore.modules.fileManagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bracket.common.Bus.ResponseJson;
import com.fileStore.modules.fileManagement.model.bo.FsFileDirectory;
import com.fileStore.modules.fileManagement.model.bo.FsFileDirectoryDetail;
import com.fileStore.modules.fileManagement.model.query.FsFileDirectoryDetailQuery;
import com.fileStore.modules.fileManagement.model.vo.FsFileDirectoryDetailView;
import com.fileStore.modules.fileManagement.model.vo.FsFileDirectoryView;

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
 * @description: 服务类
 * @author: 1
 * @create: 2020-09-04
 **/
public interface FsFileDirectoryDetailService extends IService<FsFileDirectoryDetail> {

    /**
     * 添加对象
     */
    ResponseJson addFsFileDirectoryDetail(FsFileDirectoryDetailQuery obj);

    /**
     * 编辑对象
     */
    ResponseJson editFsFileDirectoryDetail(FsFileDirectoryDetailQuery obj);

    /**
     * 删除对象
     */
    ResponseJson deleteFsFileDirectoryDetail(FsFileDirectoryDetailQuery obj);

    /**
     * 批量删除对象
     */
    ResponseJson batchDeleteFsFileDirectoryDetail(String[] ids);

    /**
     * 分页系统
     */
    IPage<FsFileDirectoryDetailView> selectFsFileDirectoryDetailPageList(FsFileDirectoryDetailQuery queryObject);


    /**
     * 根据Id获取 单个对象
     *
     * @param id
     * @return
     */
    FsFileDirectoryDetail selectFsFileDirectoryDetailById(String id);

    /**
     * 根据Ids获取多个对象
     *
     * @param
     */
    List<FsFileDirectoryDetail> selectBatchFsFileDirectoryDetailByIds(List<String> idList);

    /**
     * 根据条件获取某个对象
     *
     * @param queryObject
     * @return
     */
    FsFileDirectoryDetail selectFsFileDirectoryDetailOne(QueryWrapper<FsFileDirectoryDetail> queryObject);

    /**
     * 根据自定义条件获取对象实体集合
     *
     * @param queryObject
     * @return
     */
    List<FsFileDirectoryDetail> selectFsFileDirectoryDetailList(QueryWrapper<FsFileDirectoryDetail> queryObject);



}
