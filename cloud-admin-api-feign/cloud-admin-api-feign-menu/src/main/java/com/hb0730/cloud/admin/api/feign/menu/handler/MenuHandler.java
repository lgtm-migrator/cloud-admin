package com.hb0730.cloud.admin.api.feign.menu.handler;

import com.hb0730.cloud.admin.api.feign.menu.remote.IRemoteMenu;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.JsonConvertBeanUtils;
import com.hb0730.cloud.admin.commons.menu.model.vo.SystemMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class MenuHandler {
    @Autowired
    private IRemoteMenu remoteMenu;

    /**
     * <p>
     * 根据父类id获取菜单信息
     * </p>
     *
     * @param id 父id
     * @return 菜单信息
     */
    public List<SystemMenuVO> getMenusByParentId(@NonNull Long id) {
        ResultJson result = remoteMenu.getMenusByParentId(id);
        return JsonConvertBeanUtils.convertList(result, SystemMenuVO.class);
    }

    /**
     * <p>
     * 根据id获取菜单信息
     * </P>
     *
     * @param menuId 菜单id
     * @return 菜单信息
     */
    public SystemMenuVO getMenuById(Long menuId) {
        ResultJson result = remoteMenu.getMenuById(menuId);
        return JsonConvertBeanUtils.convert(result, SystemMenuVO.class);
    }
}
