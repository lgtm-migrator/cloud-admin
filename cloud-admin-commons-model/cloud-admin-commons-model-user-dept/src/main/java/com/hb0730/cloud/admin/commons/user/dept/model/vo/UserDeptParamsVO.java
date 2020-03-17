package com.hb0730.cloud.admin.commons.user.dept.model.vo;

import lombok.Data;
import sun.rmi.runtime.Log;

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
