package com.hb0730.cloud.admin.server.menu.system.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
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
