
package com.space.cornerstone.framework.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.space.cornerstone.framework.core.exception.UtilException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 *  PropertyColumnUtil
 */
public class TableColumnUtil {

    private final static Map<Class<?>, Map<String, String>> cacheMap = new ConcurrentHashMap<>();

    public static Map<Class<?>, Map<String, String>> getMap() {
        return cacheMap;
    }

    /**
     * 根据实体class，从mybatisplus中获取对应Table的属性列名Map
     *
     * @param clazz
     * @return
     */
    private static Map<String, String> getTableFieldMap(Class<?> clazz) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        if (tableInfo == null) {
            return null;
        }
        List<TableFieldInfo> tableFieldInfos = tableInfo.getFieldList();
        if (CollUtil.isEmpty(tableFieldInfos)) {
            return null;
        }
        return tableFieldInfos.stream().collect(Collectors.toMap(TableFieldInfo::getProperty, TableFieldInfo::getColumn));
    }

    /**
     * 从本地缓存中获取属性列名map
     *
     * @param clazz
     * @return
     */
    public static Map<String, String> getPropertyColumnMap(Class<?> clazz) {
        Map<String, String> propertyColumnMap = cacheMap.get(clazz);
        if (CollUtil.isNotEmpty(propertyColumnMap)) {
            return propertyColumnMap;
        }

        // 从TableInfo中获取，并缓存到内存map中
        Map<String, String> fieldMap = getTableFieldMap(clazz);
        if (CollUtil.isEmpty(fieldMap)) {
            return null;
        } else {
            cacheMap.put(clazz, fieldMap);
            return fieldMap;
        }
    }

    /**
     * 通过实体class类型和属性名称，从缓存中获取对应的列名
     *
     * @param clazz
     * @param property
     * @return
     */
    public static String getColumn(Class<?> clazz, String property) {
        Map<String, String> propertyColumnMap = getPropertyColumnMap(clazz);
        if (CollUtil.isEmpty(propertyColumnMap)) {
            throw new UtilException("没有找到对应的实体映射对象");
        }
        String column = propertyColumnMap.get(property);
        if (StrUtil.isEmpty(column)) {
            throw new UtilException("没有找到对应的列");
        }
        return column;
    }

}
