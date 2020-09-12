package com.fileStore.modules.fileManagement.service;


import com.fileStore.modules.fileManagement.model.bo.DownloadReq;
import com.fileStore.modules.fileManagement.model.bo.UploadBase64ArrayReq;
import com.fileStore.modules.fileManagement.model.bo.UploadBase64Req;
import com.fileStore.modules.fileManagement.model.query.FsFileDirectoryDetailQuery;
import com.fileStore.modules.fileManagement.model.query.UploadBase64ArrayRes;
import com.fileStore.modules.fileManagement.model.query.UploadThumbnailRes;
import com.fileStore.modules.fileManagement.model.query.UploadVedioRes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface FileService {

    String upLoad(FsFileDirectoryDetailQuery directoryDetailQuery, MultipartFile file) throws IOException;

    String upLoad(UploadBase64Req request) throws IOException;

    String breakPointUpload(long totalSize, long fileOffset, String fileId, MultipartFile file);

//    void saveRecord(String fileId);

    void delFile(DownloadReq request) throws Exception;

    void downloadUserFile(DownloadReq request, HttpServletResponse response) throws UnsupportedEncodingException;

    UploadBase64ArrayRes upLoadArray(UploadBase64ArrayReq request) throws IOException;

    UploadVedioRes uploadAndCompressFile(MultipartFile file) throws IOException;

    UploadThumbnailRes uploadThumbnailFile(MultipartFile file) throws IOException;
}
