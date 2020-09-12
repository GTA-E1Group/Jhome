package com.fileStore.conmmon;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.fileStore.modules.fileManagement.service.FileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class AliyunOSSUtil {
    private final static Logger logger = Logger.getLogger(AliyunOSSUtil.class.getName());
    @Autowired
    ConstantConfig constantConfig;

    @Autowired
    FileService fileService;
    /**
     * 上传文件
     */
    public String upLoad(InputStream inputStream, String fileName) {
        String endpoint = constantConfig.getLXIMAGE_END_POINT();
        System.out.println("获取到的Point为:" + endpoint);
        String accessKeyId = constantConfig.getLXIMAGE_ACCESS_KEY_ID();
        String accessKeySecret = constantConfig.getLXIMAGE_ACCESS_KEY_SECRET();
        String bucketName = constantConfig.getLXIMAGE_BUCKET_NAME1();
        String fileHost = constantConfig.getLXIMAGE_FILE_HOST();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());
        String fileUrl = "";
        OSSClient client = null;
        try {
            client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 判断容器是否存在,不存在就创建
            if (!client.doesBucketExist(bucketName)) {
                client.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                client.createBucket(createBucketRequest);
            }
            // 设置文件路径和名称
            fileUrl = fileHost + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + fileName);
            // 上传文件
            PutObjectResult result = client.putObject(bucketName, fileUrl, inputStream);
            // 设置权限(公开读)
            client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        }  catch (OSSException oe) {
            logger.error(oe.getMessage());
            throw new RuntimeException(oe.getMessage());
        } catch (ClientException ce) {
            logger.error(ce.getErrorMessage());
            throw new RuntimeException(ce.getMessage());
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                client.shutdown();
            }
        }
        return fileUrl;

    }

//    /**
//     * 下载文件
//     */
//    public void download(String objectName, HttpServletResponse response) throws IOException {
//        String bucketName = constantConfig.getLXIMAGE_BUCKET_NAME1();
//        OSSClient client = null;
//        String endpoint = constantConfig.getLXIMAGE_END_POINT();
//        logger.info("获取到的Point为:" + endpoint);
//        String accessKeyId = constantConfig.getLXIMAGE_ACCESS_KEY_ID();
//        String accessKeySecret = constantConfig.getLXIMAGE_ACCESS_KEY_SECRET();
//        InputStream is = null;
//        BufferedInputStream bis = null;
//        byte[] buffer = new byte[1024];
//        try {
//            client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
//            OSSObject ossObject = client.getObject(bucketName, objectName);
//            // 读取文件内容。
//            is = ossObject.getObjectContent();
//            if (is != null) {
//                bis = new BufferedInputStream(is);
//                OutputStream os = response.getOutputStream();
//                int i = bis.read(buffer);
//                while (i != -1) {
//                    os.write(buffer, 0, i);
//                    i = bis.read(buffer);
//                }
//                fileService.saveRecord(objectName);
//            }
//        } catch (Exception oe) {
//            logger.error(oe.getMessage());
//            throw new RuntimeException(oe.getMessage());
//        } finally {
//            if(is !=null ) {
//                is.close();
//            }
//            if (client != null) {
//                client.shutdown();
//            }
//        }
//    }

    /**
     * 删除文件
     */
    public void delete(String objectName) {
        OSSClient client = null;
        String endpoint = constantConfig.getLXIMAGE_END_POINT();
        logger.info("获取到的Point为:" + endpoint);
        String accessKeyId = constantConfig.getLXIMAGE_ACCESS_KEY_ID();
        String accessKeySecret = constantConfig.getLXIMAGE_ACCESS_KEY_SECRET();
        String bucketName = constantConfig.getLXIMAGE_BUCKET_NAME1();
        String fileHost = constantConfig.getLXIMAGE_FILE_HOST();
        try {
            client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            client.deleteObject(bucketName, objectName);
        } catch (OSSException oe) {
            logger.error(oe.getMessage());
            throw new RuntimeException(oe.getMessage());
        } catch (ClientException ce) {
            logger.error(ce.getErrorMessage());
            throw new RuntimeException(ce.getMessage());
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
    }
}

