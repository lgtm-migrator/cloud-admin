package com.hb0730.cloud.admin.server.oauth2.model;

import com.hb0730.cloud.admin.common.util.SecurityCommonConstant;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class UserDetail extends com.hb0730.cloud.admin.commons.model.security.UserDetail implements UserDetails {
    private static final long serialVersionUID = 1L;
    private long userId;
    private String username;
    private String password;
    private Integer status;
    private String perms;

    public UserDetail() {
    }

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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return SecurityCommonConstant.USER_STATUS_NORMAL.intValue() == this.status;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return SecurityCommonConstant.USER_STATUS_NORMAL.intValue() == this.status;
    }


}
