package com.cornerstone.file.oss.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import com.cornerstone.file.oss.OSSService;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * ALiYunOSSService
 *
 * @author chen qi
 * @date 2021-06-15 16:14
 **/
@RequiredArgsConstructor
public class ALiYunOSSService implements OSSService {

    private final OSS ossClient;


    /**
     * 上传对象
     *
     * @param bucketName  bucketName
     * @param objectName  objectName
     * @param inputStream inputStream
     * @param contentType contentType
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    @Override
    public String putObject(String bucketName, String objectName, InputStream inputStream, String contentType) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        ossClient.putObject(bucketName, objectName, inputStream, objectMetadata);
        return getObjectUrl(bucketName, objectName);
    }

    /**
     * 下载文件
     *
     * @param bucketName bucketName
     * @param objectName objectName
     * @author cqmike
     * @since 1.0.0
     * @return
     */
    @Override
    public InputStream getObject(String bucketName, String objectName) {
        return ossClient.getObject(bucketName, objectName).getObjectContent();
    }

    /**
     * 获取文件地址
     *
     * @param bucketName bucketName
     * @param objectName objectName
     * @author cqmike
     * @since 1.0.0
     * @return
     */
    @Override
    public String getObjectUrl(String bucketName, String objectName) {
        final URL url = ossClient.generatePresignedUrl(bucketName, objectName, new Date(new Date().getTime() + 3600 * 1000));
        return url.toString();
    }

    /**
     * 删除文件对象
     *
     * @param bucketName bucketName
     * @param objectName objectName
     * @author cqmike
     * @since 1.0.0
     * @return
     */
    @Override
    public void deleteObject(String bucketName, String objectName) {
        ossClient.deleteObject(bucketName, objectName);
    }
}
