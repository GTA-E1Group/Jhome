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
public class UploadThumbnailRes implements Serializable {

    private static final long serialVersionUID = 1219048097263458327L;

    public UploadThumbnailRes(String fileId) {
        this.fileId = fileId;
    }

    @ApiModelProperty(value = "文件id", required = true)
    private String fileId;

    @ApiModelProperty(value = "缩略图文件名称", required = true)
    private String thumbnailName;

    @ApiModelProperty(value = "缩略图文件id", required = true)
    private String thumbnailFileId;
}
