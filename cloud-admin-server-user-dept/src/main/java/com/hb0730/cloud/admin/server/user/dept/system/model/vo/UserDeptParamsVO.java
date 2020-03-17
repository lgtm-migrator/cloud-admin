package com.hb0730.cloud.admin.server.user.dept.system.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class UserDeptParamsVO implements Serializable {
    private static final long serialVersionUID = -5L;
    /**
     * <p>
     * 用户id
     * </p>
     */
    private Long userId;

    /**
     * 组织id
     */
    private Long deptId;
}
