package com.space.cornerstone.framework.core.xss;

import cn.hutool.core.util.EscapeUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName XssJacksonDeserializer.java
 * @Description Jackson入参数字符串转义处理
 * @createTime 2021年05月30日 01:30:00
 */
public class XssJacksonDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return EscapeUtil.escapeHtml4(jsonParser.getText());
    }

}
