package com.hb0730.cloud.admin.server.menu.system.vo;

import com.hb0730.cloud.admin.common.web.vo.BusinessDomainVO;
import lombok.*;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SystemMenuVO extends BusinessDomainVO {
    /**
     * 是否删除
     */
    private Integer isDelete = 0;

    /**
     * 是否启用
     */
    private Integer isEnabled = 1;

    /**
     * id
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 英文菜单名称
     */
    private String enname;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 父类菜单
     */
    private Long parentId;

    /**
     * 是否有下级
     */
    private Integer hasChild;

    /**
     * 是否根节点
     */
    private Integer isRoot;

    /**
     * 菜单级别
     */
    private Integer level;

    /**
     * 备注
     */
    private String description;
}
