package com.space.cornerstone.framework.core.domain.model;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.domain.entity.LogicDeleteEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName LoginUserDto.java
 * @Description LoginUserDto
 * @createTime 2021年05月25日 20:32:00
 */
@Data
@Accessors(chain = true)
public class LoginUserDto extends LogicDeleteEntity {

    private static final long serialVersionUID = 7713785267138287693L;
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
     * 密码
     */
    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
