package com.hb0730.cloud.admin.commons.model.security;

import com.hb0730.cloud.admin.commons.dept.model.vo.SystemDeptVO;
import com.hb0730.cloud.admin.commons.permission.model.vo.SystemPermissionVO;
import com.hb0730.cloud.admin.commons.user.model.vo.SystemUserVO;
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
}
