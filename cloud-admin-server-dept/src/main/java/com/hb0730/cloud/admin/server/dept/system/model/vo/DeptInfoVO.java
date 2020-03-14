package com.hb0730.cloud.admin.server.dept.system.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptInfoVO extends SystemDeptVO {
    private String parentNumber;
}
