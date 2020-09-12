package com.fileStore.modules.fileManagement.model.query;

import java.util.Date;
import com.bracket.common.Bus.AbstractModel.PageInfoRequest;
import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;


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
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)

@ApiModel(value = "FsFileDirectoryQuery 查询对象", description = "")
public class FsFileDirectoryQuery extends PageInfoRequest {


                private String id;

    @ApiModelProperty(value = "文件目录")
        private String fileDirectoryName;

    @ApiModelProperty(value = "父目录id")
        private String parentDirectoryId;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date createTime;

        private String createBy;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date updateTime;

        private String updateBy;

    @ApiModelProperty(value = "是否删除：0否 1是")
        private Boolean isDelete;

    @ApiModelProperty(value = "租户ID")
        private String tenantId;


    @Override
    public String toString(){
        return"FsFileDirectoryQuery{"+
                "id="+ id +
                ", fileDirectoryName="+ fileDirectoryName +
                ", parentDirectoryId="+ parentDirectoryId +
                ", createTime="+ createTime +
                ", createBy="+ createBy +
                ", updateTime="+ updateTime +
                ", updateBy="+ updateBy +
                ", isDelete="+ isDelete +
                ", tenantId="+ tenantId +
        "}";
    }
 }