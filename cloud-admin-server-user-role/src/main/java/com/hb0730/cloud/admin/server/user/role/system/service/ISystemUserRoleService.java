package com.hb0730.cloud.admin.server.user.role.system.service;

import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.user.role.system.model.entity.SystemUserRoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <p>
 * 用户角色  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
public interface ISystemUserRoleService extends IService<SystemUserRoleEntity> {
    /**
     * 根据用户id绑定组织id
     *
     * @param userId     用户id
     * @param roleIds    岗位id
     * @param userDetail 当前用户
     * @return 是否成功
     */
    boolean bindingRoleByUserId(@NonNull Long userId, @NonNull List<Long> roleIds, @NonNull UserDetail userDetail);
}
