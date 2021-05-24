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
public class SysUserParam extends BaseParam {

    private static final long serialVersionUID = 1100838202439277025L;


    private String nickname;
    private Long deptId;
    private Boolean active;

    private LocalDate beginTime;
    private LocalDate endTime;
}
