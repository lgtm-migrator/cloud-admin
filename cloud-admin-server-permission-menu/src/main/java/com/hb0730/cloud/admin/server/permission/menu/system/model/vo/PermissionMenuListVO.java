package com.hb0730.cloud.admin.server.permission.menu.system.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单权限集
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class PermissionMenuListVO implements Serializable {
    /**
     * <p>
     * id
     * </p>
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型(0为菜单1为权限)
     */
    private Integer type;
    /**
     * 子集
     */
    private List<PermissionMenuListVO> children;
}
