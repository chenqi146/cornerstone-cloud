package com.cornerstone.file.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * OSSAutoConfiguration
 *
 * @author chen qi
 * @date 2021-06-15 16:08
 **/
@Configuration
@EnableConfigurationProperties(OSSProperties.class)
@Import({ALiYunOSSConfiguration.class})
public class OSSAutoConfiguration {
}
