package com.hb0730.cloud.admin.commons.model.security;

import com.hb0730.cloud.admin.commons.model.security.dept.SystemDeptVO;
import com.hb0730.cloud.admin.commons.model.security.permission.SystemPermissionVO;
import com.hb0730.cloud.admin.commons.model.security.role.SystemRoleVO;
import com.hb0730.cloud.admin.commons.model.security.user.SystemUserVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * oauth认证后的用户信息
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDetail extends SystemUserVO {
    private static final long serialVersionUID = -18396L;
    /**
     * 用户组织
     */
    private SystemDeptVO userDept;
    /**
     * 用户权限
     */
    private List<SystemPermissionVO> userPermission;

    /**
     * 用户角色信息
     */
    private List<SystemRoleVO> userRole;
}
