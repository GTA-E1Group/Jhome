package com.fileStore.modules.fileManagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bracket.common.Bus.ResponseJson;
import com.fileStore.modules.fileManagement.model.bo.FsFileDirectory;
import com.fileStore.modules.fileManagement.model.query.FsFileDirectoryQuery;
import com.fileStore.modules.fileManagement.model.vo.FsFileDirectoryView;

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
 * @description:  服务类
 * @author:  1
 * @create: 2020-09-04
 **/
public interface FsFileDirectoryService extends IService<FsFileDirectory> {

    /**
    * 添加对象
    */
    ResponseJson addFsFileDirectory(FsFileDirectoryQuery obj);
    /**
    * 编辑对象
    */
    ResponseJson editFsFileDirectory(FsFileDirectoryQuery obj);
    /**
    * 删除对象
    */
    ResponseJson deleteFsFileDirectory(FsFileDirectoryQuery obj);
    /**
    * 批量删除对象
    */
    ResponseJson batchDeleteFsFileDirectory(String[] ids);
    /**
    * 分页系统
    */
    IPage<FsFileDirectory> selectFsFileDirectoryPageList(FsFileDirectoryQuery queryObject);


    /**
     * 根据Id获取 单个对象
     *
     * @param id
     * @return
     */
    FsFileDirectory selectFsFileDirectoryById(String id);

    /**
     * 根据Ids获取多个对象
     *
     * @param
     */
    List<FsFileDirectory> selectBatchFsFileDirectoryByIds(List<String> idList);

    /**
     * 根据条件获取某个对象
     * @param queryObject
     * @return
     */
    FsFileDirectory selectFsFileDirectoryOne(QueryWrapper<FsFileDirectory> queryObject);

    /**
     * 根据自定义条件获取对象实体集合
     * @param queryObject
     * @return
     */
    List<FsFileDirectory> selectFsFileDirectoryList(QueryWrapper<FsFileDirectory> queryObject);


    List<FsFileDirectoryView> selectFsFileDirectoryListByTree(QueryWrapper<FsFileDirectory> queryObject);
        }
