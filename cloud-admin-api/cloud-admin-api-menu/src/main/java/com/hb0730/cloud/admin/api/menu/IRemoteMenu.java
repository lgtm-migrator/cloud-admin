package com.hb0730.cloud.admin.api.menu;

import com.hb0730.cloud.admin.common.web.response.ResultJson;

/**
 * <p>
 * feign调用
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface IRemoteMenu {

    /**
     * <p>
     * 根据id获取菜单
     * </p>
     *
     * @param id id
     * @return 菜单
     */
    ResultJson getMenuById(Long id);


    /**
     * <p>
     * 根据父id获取菜单
     * </p>
     *
     * @param parentId 父id
     * @return 菜单
     */
    ResultJson getMenusByParentId(Long parentId);
}
