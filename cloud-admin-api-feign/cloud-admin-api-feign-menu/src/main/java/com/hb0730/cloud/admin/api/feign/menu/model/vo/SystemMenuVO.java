package com.hb0730.cloud.admin.api.feign.menu.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hb0730.cloud.admin.common.web.vo.BusinessDomainVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemMenuVO extends BusinessDomainVO {
    /**
     * 是否删除
     */
    private Integer isDelete = 0;

    /**
     * 是否启用
     */
    private Integer isEnabled;

    /**
     * id
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
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
     * 排序
     */
    private Integer sort;
    /**
     * 备注
     */
    private String description;
}
