
package com.space.cornerstone.framework.core.domain.model;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.CaseFormat;
import com.space.cornerstone.framework.core.util.TableColumnUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 排序列映射
 * OrderMapping
 **/
@Data
@Accessors(chain = true)
public class OrderMapping {

    /**
     * 是否开启下划线模式
     */
    private boolean underLineMode;

    public OrderMapping() {

    }

    public OrderMapping(boolean underLineMode) {
        this.underLineMode = underLineMode;
    }

    private Map<String, String> map = new ConcurrentHashMap<>();

    public OrderMapping mapping(String property, String column) {
        map.put(property, column);
        return this;
    }

    public OrderMapping mapping(String property, String tablePrefix, String column) {
        if (StrUtil.isNotBlank(tablePrefix)) {
            column = tablePrefix + StrUtil.DOT + column;
        }
        map.put(property, column);
        return this;
    }


    public OrderMapping mapping(String property, Class<?> clazz) {
        String column = TableColumnUtil.getColumn(clazz, property);
        map.put(property, column);
        return this;
    }

    public OrderMapping mapping(String property, String tablePrefix, Class<?> clazz) {
        String column = TableColumnUtil.getColumn(clazz, property);
        mapping(property, tablePrefix, column);
        return this;
    }

    public String getMappingColumn(String property) {
        if (StrUtil.isBlank(property)) {
            return null;
        }
        return map.get(property);
    }

    public void filterOrderItems(List<OrderItem> orderItems) {
        if (CollUtil.isEmpty(orderItems)) {
            return;
        }
        // 如果集合不为空，则按照PropertyColumnUtil映射
        if (CollUtil.isNotEmpty(map)) {
            orderItems.forEach(item -> item.setColumn(this.getMappingColumn(item.getColumn())));
        } else if (underLineMode) {
            // 如果开启下划线模式，自动转换成下划线
            orderItems.forEach(item -> {
                String column = item.getColumn();
                if (StringUtils.isNotBlank(column)) {
                    // 驼峰转换成下划线
                    item.setColumn(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, column));
                }
            });
        }
    }

}
