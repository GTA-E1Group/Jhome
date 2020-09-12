package com.fileStore.modules.fileManagement.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="书架列表返回对象")
public class ListBookshelfResponse implements Serializable {
    private static final long serialVersionUID = -4528005821220298891L;
    @ApiModelProperty(value="书架ID",name="bookshelfId",example="发动机原理",required=true)
    private long bookshelfId;
    @ApiModelProperty(value="教材ID",name="textbookId",example="发动机原理",required=true)
    private long textbookId;
    @ApiModelProperty(value="教材名称",name="name",example="发动机原理",required=true)
    private String name;
    @ApiModelProperty(value="作者",name="author",example="王志",required=true)
    private String author;
    @ApiModelProperty(value="教材封面路径",name="filePath",example="/fastdfs/group1/M00/00/00/CgGJeV0m2zOAWoPMAAG9GlHM7Ic542.jpg",required=true)
    private String filePath;
    @ApiModelProperty(value="教材章节资源表ID",name="textbookCategoryResourceId",example="644699827135053825",required=true)
    private long textbookCategoryResourceId;
    @ApiModelProperty(value="更新状态（0没更新，1有更新）",name="updateStatus",example="0",required=true)
    private Integer updateStatus;
    @ApiModelProperty(value="新加入状态（0已加入，1新加入",name="newJoinStatus",example="0",required=true)
    private Integer newJoinStatus;
    @ApiModelProperty(value="教材简介", name="description", required=true)
    private String description;
}
