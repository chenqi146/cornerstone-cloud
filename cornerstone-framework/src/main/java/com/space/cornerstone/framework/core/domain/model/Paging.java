package com.space.cornerstone.framework.core.domain.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName Paging.java
 * @Description Paging 返回
 * @createTime 2021年05月20日 23:32:00
 */
@Data
@Slf4j
@Builder
@AllArgsConstructor
public class Paging<T> implements Serializable {

    private static final long serialVersionUID = 3137639624281572824L;

    /**
     * 总数
     */
    private long total = 0;

    /**
     * 分页数据
     */
    private List<T> records = Collections.emptyList();

    /**
     * 分页页码
     */
    private Long pageNum;

    /**
     * 分页页数量
     */
    private Long pageSize;

    public Paging() {

    }

    public Paging(IPage<T> page) {
        this.total = page.getTotal();
        this.records = page.getRecords();
        this.pageNum = page.getCurrent();
        this.pageSize = page.getSize();
    }

    public static <T> Paging.PagingBuilder<T> builder() {
        return new Paging.PagingBuilder<>();
    }

    public static class PagingBuilder<T> {
        private long total;
        private List<T> records;
        private Long pageNum;
        private Long pageSize;

        PagingBuilder() {
        }

        public Paging.PagingBuilder<T> total(long total) {
            this.total = total;
            return this;
        }

        public Paging.PagingBuilder<T> records(List<T> records) {
            this.records = records;
            return this;
        }

        public Paging.PagingBuilder<T> pageNum(Long pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        public Paging.PagingBuilder<T> pageSize(Long pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Paging<T> build() {
            return new Paging<>(this.total, this.records, this.pageNum, this.pageSize);
        }

        public <T> Paging<T> build(IPage<T> page) {
            return new Paging<>(page);
        }

        public String toString() {
            return "Paging.PagingBuilder(total=" + this.total + ", records=" + this.records + ", pageNum=" + this.pageNum + ", pageSize=" + this.pageSize + ")";
        }
    }
}
