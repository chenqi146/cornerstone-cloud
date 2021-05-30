/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.space.cornerstone.framework.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作日志类型枚举
 *
 **/
@Getter
@AllArgsConstructor
public enum OperationLogType implements BaseEnum<Integer> {
    /**
     * 其它
     **/
    OTHER(0, "其它"),
    /**
     * 添加
     **/
    ADD(1, "添加"),
    /**
     * 修改
     **/
    UPDATE(2, "修改"),
    /**
     * 删除
     **/
    DELETE(3, "删除"),
    /**
     * 查询
     **/
    query(4, "详情查询"),
    /**
     * 详情查询
     **/
    INFO(5, "详情查询"),
    /**
     * 列表查询
     **/
    LIST(6, "列表查询"),
    /**
     * 分页列表
     **/
    PAGE(7, "分页列表"),
    /**
     * 其它查询
     **/
    OTHER_QUERY(8, "其它查询"),
    /**
     * 文件上传
     **/
    UPLOAD(9, "文件上传"),
    /**
     * 文件下载
     **/
    DOWNLOAD(10, "文件下载"),
    /**
     * Excel导入
     **/
    EXCEL_IMPORT(11, "Excel导入"),
    /**
     * Excel导出
     **/
    EXCEL_EXPORT(12, "Excel导出");

    private final Integer code;
    private final String message;

    /**
     * @throws
     * @title getMessage
     * @description 获取枚举 描述
     * @author chen qi
     * @updateTime 2021-05-18 8:42
     * @return: java.lang.String
     */
    @Override
    public String getMessage() {
        return message;
    }
}
