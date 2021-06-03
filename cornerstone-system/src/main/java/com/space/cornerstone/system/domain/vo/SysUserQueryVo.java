package com.space.cornerstone.system.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName SysUserQueryVo.java
 * @Description SysUserQueryVo
 * @createTime 2021年05月24日 23:38:00
 */
@Data
public class SysUserQueryVo {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 登录来源 默认0 -本地
     */
    private Integer source;
    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户类型（ADMIN系统用户）
     */
    private String type;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private Integer sex;

    /**
     * 头像地址
     */
    private String image;

    /**
     * 帐号状态（1正常 0停用）
     */
    private Boolean active;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private Boolean deleteFlag;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;

    /**
     * 是否锁住密码 true-是
     */
    private Boolean lockFlag;

    /**
     * 备注
     */
    private String remark;
}
