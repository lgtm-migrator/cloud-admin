package com.hb0730.cloud.admin.server.user.dept.system.service;

import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.user.dept.system.model.entity.SystemUserDeptEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 用户组织  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-16
 */
public interface ISystemUserDeptService extends IService<SystemUserDeptEntity> {
    /**
     * 根据用户id绑定组织id
     * @param userId 用户id
     * @param deptIds 组织id
     * @param userDetail 当前用户
     * @return 是否成功
     */
    boolean bindingDeptByUserId(@NonNull Long userId, @NonNull List<Long> deptIds, @NonNull UserDetail userDetail);
}
