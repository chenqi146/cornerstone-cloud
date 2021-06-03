package com.space.cornerstone.system.domain.param;

import com.space.cornerstone.framework.core.domain.param.BaseParam;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName SysUserParam.java
 * @Description SysUserParam
 * @createTime 2021年05月24日 23:30:00
 */
@Data
public class SysRoleParam extends BaseParam {


    /**
     * 权限code
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
