package com.hb0730.cloud.admin.server.oauth2.model;

import com.hb0730.cloud.admin.server.oauth2.utils.CommonConstant;
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
public class UserDetail implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private String username;
    private String password;
    private String salt;
    private Integer status;
    private String perms;
    private String avatar;
    private String sex;
    private Integer deptId;
    private String deptName;
    private String email;
    private String phone;
    private String describe;
    private String roleName;


//    private List<SysRole> roleList;


    public UserDetail(Integer userId, String username, String password, Integer status, String perms) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.status = status;
        this.perms = perms;
    }

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
        return CommonConstant.USER_STATUS_NORMAL.intValue() == this.status;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return CommonConstant.USER_STATUS_NORMAL.intValue() == this.status;
    }


}
