package com.hb0730.cloud.admin.server.permission.menu.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.hb0730.cloud.admin.commons.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单权限 
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_permission_menu")
public class SystemPermissionMenuEntity extends BaseDomain {

    private static final long serialVersionUID=1L;

    /**
     * 是否删除
     */
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;

    /**
     * 是否启用
     */
    @TableField("is_enabled")
    private Integer isEnabled;

    /**
     * id
     */
    @TableId("id")
    private Long id;

    /**
     * 权限id
     */
    @TableField("permission_id")
    private Long permissionId;

    /**
     * 菜单id
     */
    @TableField("menu_id")
    private Long menuId;

    /**
     * 备注
     */
    @TableField("description")
    private String description;


    public static final String IS_DELETE = "is_delete";

    public static final String IS_ENABLED = "is_enabled";

    public static final String ID = "id";

    public static final String PERMISSION_ID = "permission_id";

    public static final String MENU_ID = "menu_id";

    public static final String DESCRIPTION = "description";

}
