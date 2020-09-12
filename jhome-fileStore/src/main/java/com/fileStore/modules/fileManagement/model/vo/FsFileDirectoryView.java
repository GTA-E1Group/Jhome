package com.fileStore.modules.fileManagement.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
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
 * @description: 渲染视图
 * @author: Daxv
 * @create: 2020-09-04 16:47
 **/
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@ApiModel(value="资源目录")
@JsonIgnoreProperties(ignoreUnknown = true, value = {})
public class FsFileDirectoryView implements Serializable {
    @ApiModelProperty(value = "资源ID",name = "资源名称",example = "")
    private String id;
    @ApiModelProperty(value = "资源名称",name = "资源名称",example = "")
    private String fileDirectoryName;
    @ApiModelProperty(value = "父级ID",name = "资源名称",example = "")
    private String parentDirectoryId;
    @ApiModelProperty(value = "子集菜单",name = "资源名称",example = "")
    private List<FsFileDirectoryView> children;
}
