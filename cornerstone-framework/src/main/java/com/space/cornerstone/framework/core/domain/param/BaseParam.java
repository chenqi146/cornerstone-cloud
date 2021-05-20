package com.space.cornerstone.framework.core.domain.param;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.space.cornerstone.framework.core.constant.Constant;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName BaseParam.java
 * @Description BaseParam
 * @createTime 2021年05月20日 23:40:00
 */
@Data
public abstract class BaseParam implements Serializable {

    private static final long serialVersionUID = 807526741192374002L;
    private Long pageNum = Constant.PAGE_NUM;

    private Long pageSize = Constant.PAGE_SIZE;

    private String searchValue;

    private List<OrderItem> orderItems;

    public Long getPageNum(Long pageNum) {
        return (pageNum == null || pageNum <= 0) ? Constant.PAGE_NUM : pageNum;
    }

    public Long getPageSize(Long pageSize) {
        return (pageSize == null || pageSize <= 0) ? Constant.PAGE_SIZE : pageSize;
    }
}
