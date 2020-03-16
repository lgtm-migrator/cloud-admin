package com.hb0730.cloud.admin.server.role.dept.system.service;

import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.role.dept.system.model.entity.SystemRoleDeptEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 系统角色组织  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-16
 */
public interface ISystemRoleDeptService extends IService<SystemRoleDeptEntity> {
    /**
     * 根据角色id获取组织id
     *
     * @param roleId 角色id
     * @return 组织id集
     */
    List<Long> getDeptIdByRoleId(@NonNull Long roleId);

    /**
     * 根据角色id绑定组织
     *
     * @param roleId  角色id
     * @param deptIds 组织id
     * @return 是否成功
     */
    boolean bindingDeptIds(@NonNull Long roleId, @NonNull List<Long> deptIds,@NonNull UserDetail userDetail);
}
