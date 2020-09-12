package com.fileStore.modules.fileManagement.controller;

import com.bracket.common.Bus.ResponseJson;
import com.fileStore.modules.fileManagement.model.bo.DownloadReq;
import com.fileStore.modules.fileManagement.model.bo.UploadBase64ArrayReq;
import com.fileStore.modules.fileManagement.model.bo.UploadBase64Req;
import com.fileStore.modules.fileManagement.model.query.FsFileDirectoryDetailQuery;
import com.fileStore.modules.fileManagement.model.query.UploadThumbnailRes;
import com.fileStore.modules.fileManagement.service.FileService;
import com.google.common.io.Files;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@Api(value = "F、资源管理-文件上传", tags = "F、资源管理-文件上传")
@RequestMapping(value = "/file/upload")
public class UpLoadController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    FileService fileService;

    /**
     * 文件上传
     */
    @PostMapping(value = "/uploadFile",produces =MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "上传文件", notes = "Requires AccessToken")
    public ResponseJson upload(@RequestParam(value = "fileDirectoryId",required = false) String fileDirectoryId, @RequestParam(value="file", required = false) MultipartFile file) throws IOException {
        logger.info("单文件上传:");
        FsFileDirectoryDetailQuery directoryDetailQuery=new FsFileDirectoryDetailQuery();
        directoryDetailQuery.setFileDirectoryId(fileDirectoryId);
        return new ResponseJson().success().setValue("data",  fileService.upLoad(directoryDetailQuery,file));
    }

    @PostMapping(value = "/uploadAndCompressFile",produces =MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "视频文件上传(生成第5帧原图和缩略图)", notes = "Requires AccessToken")
    public ResponseJson  uploadAndCompressFile(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseJson().success().setValue("data",  fileService.uploadAndCompressFile(file));

    }

    @PostMapping(value = "/uploadThumbnailFile",produces =MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "缩略图文件上传", notes = "Requires AccessToken")
    public ResponseJson uploadThumbnailFile(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseJson().success().setValue("data",  fileService.uploadThumbnailFile(file));

    }

    @PostMapping(value = "/uploadArFile",produces =MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "AR文件上传", notes = "Requires AccessToken")
    public ResponseJson uploadArFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file == null || file.getOriginalFilename() == null) {
            //Shift.fatal(HttpStatusCodesEnum.FASTDFS_REQUEST_ERR, "文件对象或文件名为空");
        }
        logger.info("获取文件名：" + file.getOriginalFilename());
        String ext = Files.getFileExtension(file.getOriginalFilename());
        if (ext.matches("^[(jpg)|(png)|(gif)]+$")) {
            return new ResponseJson().success().setValue("data",  fileService.uploadThumbnailFile(file));

        } else {
            return new ResponseJson().success().setValue("data", new UploadThumbnailRes(fileService.upLoad(null,file)));

        }
    }

    @PostMapping(value = "/uploadStr",produces =MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "上传文件Base64编码", notes = "Requires AccessToken")
    public ResponseJson uploadStr(@Validated @RequestBody UploadBase64Req request, BindingResult result) throws IOException {
        logger.info("上传文件Base64编码");
        return new ResponseJson().success().setValue("data",  fileService.upLoad(request));

    }

    @PostMapping(value = "/uploadStrArray",produces =MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "上传文件Base64编码", notes = "Requires AccessToken")
    public ResponseJson  uploadStrArray(@Validated @RequestBody UploadBase64ArrayReq request, BindingResult result) throws IOException {
        logger.info("上传文件Base64编码");
        return new ResponseJson().success().setValue("data",  fileService.upLoadArray(request));

    }

    @PostMapping(value = "/delFile",produces =MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "删除文件", notes = "Requires AccessToken")
    public ResponseJson  delDfsFile(@Validated @RequestBody DownloadReq request, BindingResult result) throws Exception {
        logger.info("删除文件");
        fileService.delFile(request);
        return new ResponseJson().success();
    }

    @PostMapping(value = "/beakPointUpload",produces =MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "断点上传", notes = "Requires AccessToken")
    public ResponseJson beakPointUpload(@ApiParam(value = "文件id（首快上传时为空，后续分块上传必须带上）", required = true) @RequestParam("fileId") String fileId,
                                             @ApiParam(value = "分块偏移量", required = true) @RequestParam("fileOffset") long fileOffset,
                                             @ApiParam(value = "文件总大小", required = true) @RequestParam("totalSize") long totalSize,
                                             @ApiParam(value = "文件", required = true) @RequestParam("file") MultipartFile file) {
        logger.info("断点续传,fileId:"+fileId+",fileOffset:"+fileOffset );
        if(file.isEmpty()) {
            //Shift.fatal(BasicRestStatusEnum.INVALID_MODEL_FIELDS, "file must be not null");
        }else if(fileOffset > 0 && StringUtils.isBlank(fileId)) {
            //Shift.fatal(BasicRestStatusEnum.INVALID_MODEL_FIELDS, "fileId must be not null");
        }
        return new ResponseJson().success().setValue("data", fileService.breakPointUpload(totalSize, fileOffset, fileId, file));

    }

    @PostMapping(value = "/downloadUserFile",produces =MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "用户类型文件下载", notes = "Download user files",produces="application/octet-stream")
    public void downloadUserFile(@Validated @RequestBody DownloadReq request, HttpServletResponse response, BindingResult result) throws  UnsupportedEncodingException {
        logger.info("下载文件:");
        fileService.downloadUserFile(request, response);
    }
}
