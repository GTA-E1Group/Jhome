package com.fileStore.modules.fileManagement.model.query;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.bracket.common.Bus.AbstractModel.PageInfoRequest;
import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


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
 * @description: 对象
 * @author:  2
 * @create: 2020-09-04
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FsFileDirectoryDetailQuery 查询对象", description = "")
public class FsFileDirectoryDetailQuery extends PageInfoRequest {

    private String id;
    @ApiModelProperty(value = "文件目录")
        private String fileDirectoryId;

    @ApiModelProperty(value = "文件类型")
        private String fileType;

    @ApiModelProperty(value = "文件名称")
        private String fileName;

    @ApiModelProperty(value = "文件下载次数")
        private Integer fileDownloadCount;

    @ApiModelProperty(value = "文件大小")
        private Integer fileSize;

    @ApiModelProperty(value = "文件地址")
        private String fileUrl;

    @ApiModelProperty(value = "文件备注")
        private String fileRemarks;

    @ApiModelProperty(value = "是否删除：0否 1是")
        private Boolean isDelete;

    @ApiModelProperty(value = "租户ID")
        private String tenantId;

    private String updateBy;
    private String createBy;
 }