package com.hb0730.cloud.admin.server.dept.system.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 树形组织
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTreeVO extends SystemDeptVO {

    /**
     * 子集
     */
    private List<DeptTreeVO> children;
}
