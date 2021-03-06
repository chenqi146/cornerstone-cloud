package com.space.cornerstone.framework.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.space.cornerstone.framework.core.redis.RedisClient;

/**
 * RedisTemplateConfig
 *
 * @author chen qi
 * @date 2021-05-18 15:07
 **/
@Slf4j
@Configuration
public class RedisTemplateConfig {

    /**
     * 自定義 redisTemplate （方法名一定要叫 redisTemplate 因為 @Bean 是根據方法名配置這個bean的name的）
     * 默認的 RedisTemplate<K,V> 為泛型，使用時不太方便，自定義為 <String, Object>
     * 默認序列化方式為 JdkSerializationRedisSerializer 序列化後的內容不方便閱讀，改為序列化成 json
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean("redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("redis初始化序列方式！");
        // 配置 json 序列化器 - Jackson2JsonRedisSerializer
        Jackson2JsonRedisSerializer<Object> jacksonSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL);
        // 解决jackson2无法反序列化LocalDateTime的问题
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 忽略未知属性，防止json字符串中存在，java对象中不存在对应属性的情况出现错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        jacksonSerializer.setObjectMapper(objectMapper);
        // 創建並配置自定義 RedisTemplateRedisOperator
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 將 key 序列化成字符串
        template.setKeySerializer(new StringRedisSerializer());
        // 將 hash 的 key 序列化成字符串
        template.setHashKeySerializer(new StringRedisSerializer());
        // 將 value 序列化成 json
        template.setValueSerializer(jacksonSerializer);
        // 將 hash 的 value 序列化成 json
        template.setHashValueSerializer(jacksonSerializer);
        template.afterPropertiesSet();
        return template;
    }


    @Bean("redisClient")
    public RedisClient redisClient(@Qualifier("redisTemplate") RedisTemplate redisTemplate) {
        return new RedisClient(redisTemplate);
    }
}
