package com.cornerstone.file.oss;

import java.io.InputStream;

/**
 * Oss服务接口
 *
 * @author chen qi
 * @date 2021-06-15 15:57
 **/
public interface OSSService {


    /**
     * 上传对象
     *
     * @author cqmike
     * @param bucketName bucketName
     * @param objectName objectName
     * @param inputStream inputStream
     * @param contentType contentType
     * @since 1.0.0
     * @return
     */
    String putObject(String bucketName, String objectName, InputStream inputStream, String contentType);

    /**
     * 下载文件
     *
     * @author cqmike
     * @param bucketName bucketName
     * @param objectName objectName
     * @since 1.0.0
     * @return
     */
    InputStream getObject(String bucketName, String objectName);

    /**
     * 获取文件地址
     *
     * @author cqmike
     * @param bucketName bucketName
     * @param objectName objectName
     * @since 1.0.0
     * @return 有效期一小时
     */
    String getObjectUrl(String bucketName, String objectName);

    /**
     * 删除文件对象
     *
     * @author cqmike
     * @param bucketName bucketName
     * @param objectName objectName
     * @since 1.0.0
     * @return
     */
    void deleteObject(String bucketName, String objectName);

    // TODO: 2021/6/15 上传回调待接入

}
