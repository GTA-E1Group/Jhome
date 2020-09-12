package com.fileStore.modules.fileManagement.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

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
 * @description:
 * @author: Daxv
 * @create: 2020-09-05 09:50
 **/
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@ToString
@ApiModel(value="资源列表明细")
public class FsFileDirectoryDetailView implements Serializable {
    @ApiModelProperty(value = "主键",name = "id")
    private String id;
    @ApiModelProperty(value = "文件名称",name = "fileName")
    private String fileName;
    @ApiModelProperty(value = "文件下载次数",name = "fileDownloadCount")
    private Integer fileDownloadCount;
    @ApiModelProperty(value = "文件大小",name = "fileSize")
    private Integer fileSize;
    @ApiModelProperty(value = "文件备注",name = "fileRemarks")
    private String fileRemarks;
}
