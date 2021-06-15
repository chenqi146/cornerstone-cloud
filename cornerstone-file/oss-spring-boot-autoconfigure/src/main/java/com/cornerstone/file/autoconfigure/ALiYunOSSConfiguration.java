package com.cornerstone.file.autoconfigure;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.cornerstone.file.oss.OSSService;
import com.cornerstone.file.oss.impl.ALiYunOSSService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AliyunOSSConfiguration
 *
 * @author chen qi
 * @date 2021-06-15 16:10
 **/
@Configuration
@ConditionalOnProperty(prefix = "oss.aliyun", name = "endpoint")
@ConditionalOnClass(OSSClient.class)
public class ALiYunOSSConfiguration {

    @Bean
    public OSSService aLiYunOSSService(OSSProperties ossProperties) {
        OSSProperties.ALiYun aLiYun = ossProperties.getAliyun();

        OSS ossClient = new OSSClientBuilder().build(aLiYun.getEndpoint(), aLiYun.getAccessKeyId(), aLiYun.getAccessKeySecret());
        return new ALiYunOSSService(ossClient);
    }
}
