package com.hb0730.cloud.admin.server.oauth2.model;

import com.google.common.collect.Sets;
import com.hb0730.clou.admin.commons.model.role.SystemRoleVO;
import com.hb0730.cloud.admin.common.util.SecurityCommonConstant;
import com.hb0730.cloud.admin.commons.permission.model.vo.SystemPermissionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private static final String ROLE = "ROLE_";

    public UserDetail() {
    }

    /**
     * 用户的权限集， 默认需要添加ROLE_ 前缀
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SystemPermissionVO> userPermission = getUserPermission();
        String perms = "";
        if (!CollectionUtils.isEmpty(userPermission)) {
            List<String> permissions = userPermission.parallelStream().map(SystemPermissionVO::getUrl).collect(Collectors.toList());
            perms = StringUtils.join(permissions.toArray(), ",");
        }
        List<SystemRoleVO> userRole = getUserRole();
        if (!CollectionUtils.isEmpty(userRole)) {
            Set<String> s = Sets.newConcurrentHashSet();
            userRole.parallelStream().forEach(role -> {
                s.add(ROLE + role.getEnname());
            });
            perms += "," + StringUtils.join(s.toArray(), ",");
        }
        return AuthorityUtils.commaSeparatedStringToAuthorityList(perms);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
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
        return SecurityCommonConstant.USER_STATUS_NORMAL.intValue() == super.getIsEnabled();
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
        return SecurityCommonConstant.USER_STATUS_NORMAL.intValue() == super.getIsEnabled();
    }


}
