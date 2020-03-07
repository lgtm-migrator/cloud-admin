package com.hb0730.cloud.admin.commons.menu.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 树形菜单
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuVO extends SystemMenuVO {

    /**
     * 子菜单
     */
    private List<MenuVO> childrens;
}
