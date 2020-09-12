package com.fileStore.modules.fileManagement.model.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class UploadVedioRes implements Serializable {

    private static final long serialVersionUID = -4193856771468870783L;
    @ApiModelProperty(value = "视频文件id", required = true)
    private String vedioFileId;

    @ApiModelProperty(value = "原图文件名称", required = true)
    private String srcImageName;

    @ApiModelProperty(value = "原图文件id", required = true)
    private String srcImageFileId;

    @ApiModelProperty(value = "缩略图文件名称", required = true)
    private String thumbnailName;

    @ApiModelProperty(value = "缩略图文件id", required = true)
    private String thumbnailFileId;
}
