package com.hb0730.cloud.admin.server.permission.menu.system.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class PermissionMenuDTO implements Serializable {
    private Long menuId;
    private Long permissionId;
    private Long createUserId;
    private Date createTime;
}
