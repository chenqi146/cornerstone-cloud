package com.space.cornerstone.framework.core.domain.entity.system;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.domain.entity.LogicDeleteEntity;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 用户表
 */
@Data
public class SysUser extends LogicDeleteEntity {
    private static final long serialVersionUID = 6929137513399914731L;

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
     * 密码
     */
    @JsonIgnore
    @JsonProperty
    private String password;

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

    public boolean isAdmin() {
        if (StrUtil.isEmpty(type)) {
            return false;
        }

        return Objects.equals(Constant.USER_TYPE_ADMIN, type);
    }


}

