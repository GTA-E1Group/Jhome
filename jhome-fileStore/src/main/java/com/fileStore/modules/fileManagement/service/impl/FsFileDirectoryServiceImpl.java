package com.fileStore.modules.fileManagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bracket.common.Bus.ResponseJson;
import com.bracket.common.Bus.Status;
import com.bracket.common.ToolKit.ObjectBaseUtils;
import com.bracket.common.ToolKit.StringUtil;
import com.fileStore.modules.fileManagement.dao.FsFileDirectoryMapper;
import com.fileStore.modules.fileManagement.model.bo.FsFileDirectory;
import com.fileStore.modules.fileManagement.model.query.FsFileDirectoryQuery;
import com.fileStore.modules.fileManagement.model.vo.FsFileDirectoryView;
import com.fileStore.modules.fileManagement.service.FsFileDirectoryService;
import com.sun.mail.imap.protocol.ID;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.metadata.IPage;

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
 * @description: 服务实现类
 * @author: 1
 * @create: 2020-09-04
 **/
@Service
public class FsFileDirectoryServiceImpl extends ServiceImpl<FsFileDirectoryMapper, FsFileDirectory> implements FsFileDirectoryService {

    @Autowired
    protected FsFileDirectoryMapper mapper;

    @Override
    public ResponseJson addFsFileDirectory(FsFileDirectoryQuery obj) {
        String massAge = "";
        try {
            FsFileDirectory targetObj = ObjectBaseUtils.copyProperties(obj, FsFileDirectory.class);
            targetObj.setId(UUID.randomUUID().toString());
            targetObj.setCreateTime(new Date());
            targetObj.setUpdateTime(new Date());
            int count = mapper.insert(targetObj);
            if (count > 0)
                return new ResponseJson().success();
            return new ResponseJson().error(Status.SC_INTERNAL_SERVER_ERROR, "新增失败~");
        } catch (Exception ex) {
            massAge = ex.getMessage();
        }
        return new ResponseJson().error(massAge);
    }

    @Override
    public ResponseJson editFsFileDirectory(FsFileDirectoryQuery obj) {
        String massAge = "";
        try {
            FsFileDirectory targetObj = ObjectBaseUtils.copyProperties(obj, FsFileDirectory.class);
            int count = mapper.updateById(targetObj);
            if (count > 0)
                return new ResponseJson().success();
            return new ResponseJson().error(Status.SC_INTERNAL_SERVER_ERROR, "编辑失败~");
        } catch (Exception ex) {
            massAge = ex.getMessage();
        }
        return new ResponseJson().error(massAge);
    }

    @Override
    public ResponseJson batchDeleteFsFileDirectory(String[] ids) {
        String massAge = "";
        try {

            int count = mapper.deleteBatchIds(Arrays.asList(ids));
            if (count > 0)
                return new ResponseJson().success();
            return new ResponseJson().error(Status.SC_INTERNAL_SERVER_ERROR, "删除失败~");
        } catch (Exception ex) {
            massAge = ex.getMessage();
        }
        return new ResponseJson().error(massAge);
    }

    @Override
    public ResponseJson deleteFsFileDirectory(FsFileDirectoryQuery obj) {
        String massAge = "";
        try {
            FsFileDirectory targetObj = ObjectBaseUtils.copyProperties(obj, FsFileDirectory.class);
            int count = mapper.deleteById(targetObj);
            if (count > 0)
                return new ResponseJson().success();
            return new ResponseJson().error(Status.SC_INTERNAL_SERVER_ERROR, "删除失败~");
        } catch (Exception ex) {
            massAge = ex.getMessage();
        }
        return new ResponseJson().error(massAge);
    }

    @Override
    public IPage<FsFileDirectory> selectFsFileDirectoryPageList(FsFileDirectoryQuery queryObject) {
        IPage<FsFileDirectory> page = new Page<>(queryObject.getPageNum(), queryObject.getPageSize());
        IPage<FsFileDirectory> result = mapper.selectFsFileDirectoryPageList(page, queryObject);
        return result;
    }


    /**
     * 根据Id获取 单个对象
     *
     * @param id
     * @return
     */
    @Override
    public FsFileDirectory selectFsFileDirectoryById(String id) {
        return mapper.selectById(id);
    }

    /**
     * 根据Ids获取多个对象
     *
     * @param
     */
    @Override
    public List<FsFileDirectory> selectBatchFsFileDirectoryByIds(List<String> idList) {
        return mapper.selectBatchIds(idList);
    }

    /**
     * 获取一个目标对象
     *
     * @param queryObject
     * @return
     */
    @Override
    public FsFileDirectory selectFsFileDirectoryOne(QueryWrapper<FsFileDirectory> queryObject) {
        return mapper.selectOne(queryObject);
    }

    /**
     * 根据条件 获取多个目标对象
     *
     * @param queryObject
     * @return
     */
    @Override
    public List<FsFileDirectory> selectFsFileDirectoryList(QueryWrapper<FsFileDirectory> queryObject) {
        return mapper.selectList(queryObject);
    }

    @Override
    public List<FsFileDirectoryView> selectFsFileDirectoryListByTree(QueryWrapper<FsFileDirectory> queryObject) {
        List<FsFileDirectoryView> fsFileDirectoryViewsByVo = new ArrayList<>();
        try {
            List<FsFileDirectoryView> fsFileDirectoryViews = mapper.selectList(queryObject).stream()
                    .map(t -> {
                        FsFileDirectoryView fsFileDirectoryView = new FsFileDirectoryView();
                        fsFileDirectoryView.setId(t.getId());
                        fsFileDirectoryView.setParentDirectoryId(t.getParentDirectoryId());
                        fsFileDirectoryView.setFileDirectoryName(t.getFileDirectoryName());
                        return fsFileDirectoryView;
                    }).collect(Collectors.toList());
            List<FsFileDirectoryView> finalFsFileDirectoryViews = fsFileDirectoryViews.stream().filter(c -> StringUtil.isBlank(c.getParentDirectoryId())).collect(Collectors.toList());
            finalFsFileDirectoryViews.forEach(t -> {
                t.setChildren(this.getFsFileDirectoryChild(fsFileDirectoryViews,t));
                fsFileDirectoryViewsByVo.add(t);
            });
        } catch (Exception ex) {
            throw ex;
        }
        return fsFileDirectoryViewsByVo;
    }

    public List<FsFileDirectoryView> getFsFileDirectoryChild(List<FsFileDirectoryView> fsFileDirectoryViews,FsFileDirectoryView fsFileDirectoryView) {
        //t 中数据数据进行遍历
        List<FsFileDirectoryView> children=fsFileDirectoryViews.stream().filter(e -> e.getParentDirectoryId().equals(fsFileDirectoryView.getId())).collect(Collectors.toList());
        fsFileDirectoryView.setChildren(children);
        children.forEach(t->{
            this.getFsFileDirectoryChild(fsFileDirectoryViews,t);
        });
        return children;
    }
}
