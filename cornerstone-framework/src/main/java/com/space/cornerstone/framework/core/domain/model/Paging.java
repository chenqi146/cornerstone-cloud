package com.space.cornerstone.framework.core.domain.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName Paging.java
 * @Description Paging
 * @createTime 2021年05月20日 23:32:00
 */
@Data
@Slf4j
public class Paging<T> extends Page<T> {

    private static final long serialVersionUID = 3137639624281572824L;

    private long total = 0;

    private List<T> records = Collections.emptyList();

    private Long pageNum;

    private Long pageSize;

    public Paging() {

    }

    public Paging(IPage<T> page) {
        this.total = page.getTotal();
        this.records = page.getRecords();
        this.pageNum = page.getCurrent();
        this.pageSize = page.getSize();
    }
}
