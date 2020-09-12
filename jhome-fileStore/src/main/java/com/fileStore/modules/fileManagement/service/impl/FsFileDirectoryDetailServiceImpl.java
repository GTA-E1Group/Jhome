package com.fileStore.modules.fileManagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bracket.common.Bus.ResponseJson;
import com.bracket.common.Bus.Status;
import com.bracket.common.ToolKit.ObjectBaseUtils;
import com.fileStore.modules.fileManagement.dao.FsFileDirectoryDetailMapper;
import com.fileStore.modules.fileManagement.model.bo.FsFileDirectoryDetail;
import com.fileStore.modules.fileManagement.model.query.FsFileDirectoryDetailQuery;
import com.fileStore.modules.fileManagement.model.vo.FsFileDirectoryDetailView;
import com.fileStore.modules.fileManagement.service.FsFileDirectoryDetailService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
 * @description:  服务实现类
 * @author:  1
 * @create: 2020-09-04
 **/
@Service
public class FsFileDirectoryDetailServiceImpl extends ServiceImpl<FsFileDirectoryDetailMapper, FsFileDirectoryDetail>implements FsFileDirectoryDetailService {

    @Autowired
    protected FsFileDirectoryDetailMapper mapper;

    @Override
    public ResponseJson addFsFileDirectoryDetail(FsFileDirectoryDetailQuery obj) {
        String massAge = "";
        try {
           FsFileDirectoryDetail targetObj= ObjectBaseUtils.copyProperties(obj,FsFileDirectoryDetail.class);
            targetObj.setId(UUID.randomUUID().toString());
            targetObj.setCreateTime(new Date());
            targetObj.setUpdateTime(new Date());
            int count = mapper.insert(targetObj);
            if (count > 0)
                return new ResponseJson().success();
            return new ResponseJson().error(Status.SC_INTERNAL_SERVER_ERROR,"新增失败~");
            } catch (Exception ex) {
                 massAge = ex.getMessage();
            }
        return new ResponseJson().error(massAge);
    }

    @Override
    public ResponseJson editFsFileDirectoryDetail( FsFileDirectoryDetailQuery obj) {
        String massAge = "";
        try {
        FsFileDirectoryDetail targetObj= ObjectBaseUtils.copyProperties(obj,FsFileDirectoryDetail.class);
        int count = mapper.updateById(targetObj);
            if (count > 0)
                return new ResponseJson().success();
            return new ResponseJson().error(Status.SC_INTERNAL_SERVER_ERROR,"编辑失败~");
        } catch (Exception ex) {
             massAge = ex.getMessage();
        }
        return new ResponseJson().error(massAge);
    }

    @Override
    public ResponseJson batchDeleteFsFileDirectoryDetail(String[] ids) {
        String massAge = "";
        try {

            int count = mapper.deleteBatchIds(Arrays.asList(ids));
            if (count > 0)
                return new ResponseJson().success();
            return new ResponseJson().error(Status.SC_INTERNAL_SERVER_ERROR,"删除失败~");
        } catch (Exception ex) {
            massAge = ex.getMessage();
        }
        return new ResponseJson().error(massAge);
    }

    @Override
    public ResponseJson deleteFsFileDirectoryDetail( FsFileDirectoryDetailQuery obj) {
        String massAge = "";
        try {
            FsFileDirectoryDetail targetObj= ObjectBaseUtils.copyProperties(obj,FsFileDirectoryDetail.class);
            int count = mapper.deleteById(targetObj);
            if (count > 0)
                return new ResponseJson().success();
            return new ResponseJson().error(Status.SC_INTERNAL_SERVER_ERROR,"删除失败~");
        } catch (Exception ex) {
            massAge = ex.getMessage();
        }
        return new ResponseJson().error(massAge);
    }

    @Override
    public  IPage<FsFileDirectoryDetailView> selectFsFileDirectoryDetailPageList(FsFileDirectoryDetailQuery queryObject){
        IPage<FsFileDirectoryDetailView> page = new Page<>(queryObject.getPageNum(), queryObject.getPageSize());
        IPage<FsFileDirectoryDetailView> result = mapper.selectFsFileDirectoryDetailPageList(page, queryObject);
    return result;
    }


    /**
     * 根据Id获取 单个对象
     *
     * @param id
     * @return
     */
    @Override
    public FsFileDirectoryDetail selectFsFileDirectoryDetailById(String id) {
    return mapper.selectById(id);
    }

    /**
     * 根据Ids获取多个对象
     *
     * @param
     */
    @Override
    public List<FsFileDirectoryDetail> selectBatchFsFileDirectoryDetailByIds(List<String> idList) {
    return mapper.selectBatchIds(idList);
    }

    /**
     * 获取一个目标对象
     *
     * @param queryObject
     * @return
     */
    @Override
    public FsFileDirectoryDetail selectFsFileDirectoryDetailOne(QueryWrapper<FsFileDirectoryDetail> queryObject) {
    return mapper.selectOne(queryObject);
    }

    /**
     * 根据条件 获取多个目标对象
     *
     * @param queryObject
     * @return
     */
    @Override
    public List<FsFileDirectoryDetail> selectFsFileDirectoryDetailList(QueryWrapper<FsFileDirectoryDetail> queryObject) {
    return mapper.selectList(queryObject);
    }
}
