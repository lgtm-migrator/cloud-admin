package com.hb0730.cloud.admin.server.oauth2.model;

import com.hb0730.cloud.admin.common.util.SecurityCommonConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * <p>
 * 用户认证信息
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDetail extends com.hb0730.cloud.admin.commons.model.security.UserDetail implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String username;
    private String password;
    private Integer status;
    private String perms;

    public UserDetail() {
    }

    /**
     * 用户的权限集， 默认需要添加ROLE_ 前缀
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return AuthorityUtils.commaSeparatedStringToAuthorityList(this.perms);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 账户是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return SecurityCommonConstant.USER_STATUS_NORMAL.intValue() == this.status;
    }

    /**
     * 凭证是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否可用
     */
    @Override
    public boolean isEnabled() {
        return SecurityCommonConstant.USER_STATUS_NORMAL.intValue() == this.status;
    }


}
