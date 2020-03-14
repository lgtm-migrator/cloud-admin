package com.hb0730.cloud.admin.server.dept.system.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hb0730.cloud.admin.common.web.vo.BaseDomainVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统部门
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemDeptVO extends BaseDomainVO {

    private static final long serialVersionUID = 1L;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 是否启用
     */
    private Integer isEnabled;

    /**
     * 部门id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 父id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    /**
     * 组织编码
     */
    private String number;
    /**
     * 部门名称
     */
    private String name;

    /**
     * 显示顺序
     */
    private Integer order;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
}
