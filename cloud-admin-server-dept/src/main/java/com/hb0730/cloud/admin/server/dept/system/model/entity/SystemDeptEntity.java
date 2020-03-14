package com.hb0730.cloud.admin.server.dept.system.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.hb0730.cloud.admin.commons.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@TableName("t_system_dept")
public class SystemDeptEntity extends BaseDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    @TableLogic
    private Integer isDelete;

    /**
     * 是否启用
     */
    @TableField(value = "is_enabled", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Integer isEnabled;

    /**
     * 部门id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 父id
     */
    @TableField(value = "parent_id", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Long parentId;

    /**
     * 组织编码
     */
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    /**
     * 部门名称
     */
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String name;

    /**
     * 显示顺序
     */
    @TableField(value = "`order`", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Integer order;

    /**
     * 祖级列表
     */
    @TableField(value = "ancestors", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String ancestors;

    /**
     * 负责人
     */
    @TableField(value = "leader", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String leader;

    /**
     * 联系电话
     */
    @TableField(value = "phone", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String email;


    public static final String IS_DELETE = "is_delete";

    public static final String IS_ENABLED = "is_enabled";

    public static final String ID = "id";

    public static final String PARENT_ID = "parent_id";

    public static final String NUMBER="number";

    public static final String NAME = "name";

    public static final String ORDER = "order";

    public static final String ANCESTORS = "ancestors";

    public static final String LEADER = "leader";

    public static final String PHONE = "phone";

    public static final String EMAIL = "email";

}
