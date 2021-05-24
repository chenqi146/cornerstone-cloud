
package com.space.cornerstone.framework.core.domain.model;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.space.cornerstone.framework.core.domain.param.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

/**
 *  传给mapper的page参数
 *PageInfo
 * @date 2020/3/27
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PageInfo<T> extends Page<T> {

    private static final long serialVersionUID = 5905770897913475872L;

    /**
     * 分页参数
     */
    private BaseParam pageParam;

    /**
     * 默认排序字段信息
     */
    private OrderItem defaultOrderItem;

    /**
     * 排序字段映射
     */
    private OrderMapping orderMapping;


    public PageInfo() {

    }

    /**
     * 传入分页参数
     *
     * @param pageParam
     */
    public PageInfo(BaseParam pageParam) {
        this(pageParam, null, null);
    }

    /**
     * 传入分页参数，默认排序
     *
     * @param basePageParam
     * @param defaultOrderItem
     */
    public PageInfo(BaseParam basePageParam, OrderItem defaultOrderItem) {
        this(basePageParam, defaultOrderItem, null);
    }

    /**
     * 传入分页参数，排序字段映射
     *
     * @param pageParam
     * @param orderMapping
     */
    public PageInfo(BaseParam pageParam, OrderMapping orderMapping) {
        this(pageParam, null, orderMapping);
    }

    /**
     * 传入分页参数，默认排序，排序字段映射
     *
     * @param pageParam
     * @param defaultOrderItem
     * @param orderMapping
     */
    public PageInfo(BaseParam pageParam, OrderItem defaultOrderItem, OrderMapping orderMapping) {
        this.pageParam = pageParam;
        this.defaultOrderItem = defaultOrderItem;
        this.orderMapping = orderMapping;
        this.handleOrderItems();
    }

    /**
     * 分页构造函数
     *
     * @param current 当前页
     * @param size    每页显示条数
     */
    public PageInfo(long current, long size) {
        super(current, size, 0);
    }

    public PageInfo(long current, long size, long total) {
        super(current, size, total, true);
    }

    public PageInfo(long current, long size, boolean isSearchCount) {
        super(current, size, isSearchCount);
    }

    public PageInfo(long current, long size, long total, boolean isSearchCount) {
        super(current, size, total, isSearchCount);
    }

    /**
     * 如果orderItems为空则使用默认排序
     */
    private void handleOrderItems() {
        if (pageParam == null) {
            return;
        }

        super.setCurrent(pageParam.getPageNum());
        super.setSize(pageParam.getPageSize());
        // 排序字段处理
        List<OrderItem> orderItems = pageParam.getOrderItems();

        if (CollUtil.isEmpty(orderItems)) {
            if (defaultOrderItem != null) {
                super.setOrders(Collections.singletonList(defaultOrderItem));
            }
            return;
        }

        if (orderMapping == null) {
            orderMapping = new OrderMapping(true);
        }
        orderMapping.filterOrderItems(orderItems);
        super.setOrders(orderItems);
    }

}
