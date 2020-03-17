package com.hb0730.cloud.admin.server.user.dept.system.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.hb0730.cloud.admin.commons.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户组织
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_user_dept")
public class SystemUserDeptEntity extends BaseDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    @TableLogic
    private Integer isDelete=0;

    /**
     * 是否启用
     */
    @TableField(value = "is_enabled", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Integer isEnabled;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Long userId;

    /**
     * 组织id
     */
    @TableField(value = "dept_id", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Long deptId;


    public static final String IS_DELETE = "is_delete";

    public static final String IS_ENABLED = "is_enabled";

    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String DEPT_ID = "dept_id";

}
