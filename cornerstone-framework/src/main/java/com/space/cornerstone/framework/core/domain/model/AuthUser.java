package com.space.cornerstone.framework.core.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.space.cornerstone.framework.core.domain.entity.system.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName AuthUser.java
 * @Description AuthUser
 * @createTime 2021年05月21日 23:46:00
 */
@Data
public class AuthUser implements UserDetails {
    private static final long serialVersionUID = 5093381636633535037L;

    /**
     * 用户唯一标识
     */
    private String token;

    private SysUser user;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    public AuthUser() {
    }

    public AuthUser(SysUser user, Set<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user == null ? null : user.getPassword();
    }

    @Override
    public String getUsername() {
        return user == null ? null : user.getUserName();
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return false;
    }
}
