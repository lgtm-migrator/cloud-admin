package com.hb0730.cloud.admin.server.role.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.cloud.admin.commons.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统角色
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_role")
public class SystemRoleEntity extends BaseDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 是否删除
     */
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete = 0;

    /**
     * 是否启用
     */
    @TableField("is_enabled")
    private Integer isEnabled = 1;

    /**
     * id
     */
    @TableId("id")
    private Long id;

    /**
     * 父角色
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 角色名称
     */
    @TableField("name")
    private String name;

    /**
     * 角色英文名称
     */
    @TableField("enname")
    private String enname;

    /**
     * 备注
     */
    @TableField("description")
    private String description;


    public static final String IS_DELETE = "is_delete";

    public static final String IS_ENABLED = "is_enabled";

    public static final String ID = "id";

    public static final String PARENT_ID = "parent_id";

    public static final String NAME = "name";

    public static final String ENNAME = "enname";

    public static final String DESCRIPTION = "description";

}
