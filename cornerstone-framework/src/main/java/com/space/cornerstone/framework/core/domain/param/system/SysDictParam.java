package com.space.cornerstone.framework.core.domain.param.system;

import com.space.cornerstone.framework.core.domain.param.BaseParam;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName SysDictParam.java
 * @Description SysDictParam
 * @createTime 2021年06月05日 14:47:00
 */
@Data
public class SysDictParam extends BaseParam {
    private static final long serialVersionUID = -9169955742350656567L;

    /**
     * 字典类型
     */
    private String code;

    /**
     * 开始时间
     */
    private LocalDate beginTime;

    /**
     * 结束时间
     */
    private LocalDate endTime;


}
