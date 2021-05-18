

package com.space.cornerstone.framework.core.xss;

import cn.hutool.core.util.EscapeUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Jackson响应参数字符串转义处理
 *
 * @author chen qi
 **/
@Slf4j
public class XssJacksonSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (StrUtil.isNotEmpty(s)) {
            String encodedValue = EscapeUtil.escapeHtml4(s);
            jsonGenerator.writeString(encodedValue);
        }
    }

}
