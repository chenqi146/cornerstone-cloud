package com.space.cornerstone.system.domain.entity;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.domain.entity.LogicDeleteEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 用户表
 */
@Data
@Accessors(chain = true)
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
    @JsonProperty
    private String password;

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

    /**
     * 角色列表
     */
    @TableField(exist = false)
    private List<SysRole> roles;

    @JsonIgnore
    public SysUser cloneUpdate() {
        SysUser sysUser = new SysUser()
                .setUserName(getUserName())
                .setId(getId())
                .setEmail(getEmail())
                .setPhone(getPhone())
                .setDeptId(getDeptId())
                .setRoles(getRoles())
                .setNickName(getNickName())
                .setRemark(getRemark())
                .setImage(getImage())
                .setSex(getSex())
                .setType(getType());
        sysUser.setActive(getActive());
        return sysUser;
    }


    public boolean isAdmin() {
        if (StrUtil.isEmpty(type)) {
            return false;
        }

        return Objects.equals(Constant.USER_TYPE_ADMIN, type);
    }


}

