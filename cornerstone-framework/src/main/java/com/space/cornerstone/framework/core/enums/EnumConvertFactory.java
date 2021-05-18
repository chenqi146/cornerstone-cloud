package com.space.cornerstone.framework.core.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName EnumConvertFactory.java
 * @Description 枚举转换工厂
 * @createTime 2021年05月18日 22:10:00
 */
public class EnumConvertFactory implements ConverterFactory<String , BaseEnum<?>> {

    private static final Map<Class<?>, Converter<String, ?>> converterMap = new WeakHashMap<>();

    @Override
    public <T extends BaseEnum<?>> Converter<String, T> getConverter(Class<T> targetType) {
        Converter result = converterMap.get(targetType);
        if(result == null) {
            result = new StringToIEum<>(targetType);
            converterMap.put(targetType, result);
        }
        return result;
    }

    static class StringToIEum<T extends BaseEnum<?>> implements Converter<String, T> {
        private final Map<String, T> enumMap = new HashMap<>();

        public StringToIEum(Class<T> enumType) {
            T[] enums = enumType.getEnumConstants();
            for(T e : enums) {
                enumMap.put(e.getCode() + "", e);
            }

        }
        @Override
        public T convert(String source) {
            T result = enumMap.get(source);
            if(result == null) {
                throw new IllegalArgumentException("No element matches " + source);
            }
            return result;
        }

    }
}
