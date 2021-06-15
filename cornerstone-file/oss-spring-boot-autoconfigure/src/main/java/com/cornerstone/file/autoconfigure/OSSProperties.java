package com.cornerstone.file.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * OSSProperties
 *
 * @author chen qi
 * @date 2021-06-15 16:09
 **/
@Data
@ConfigurationProperties(prefix = OSSProperties.OSS_PREFIX)
public class OSSProperties {

    static final String OSS_PREFIX = "oss";

    private ALiYun aliyun;

    /**
     * oss switch  default false
     */
    private boolean enable;

    /**
     * Aliyun OSS.
     */
    @Data
    public static class ALiYun {
        /**
         * aliyun oss endpoint
         */
        private String endpoint;

        /**
         * aliyun oss accessKeyId
         */
        private String accessKeyId;

        /**
         * aliyun oss accessKeySecret
         */
        private String accessKeySecret;
    }
}
