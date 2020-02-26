package com.hb0730.cloud.admin.server.permission.system.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单权限
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionMenuVO extends SystemPermissionVO {
    private Long menuId;
    private String menuName;
}
