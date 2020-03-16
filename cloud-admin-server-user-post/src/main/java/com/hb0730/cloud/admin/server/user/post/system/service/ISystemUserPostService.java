package com.hb0730.cloud.admin.server.user.post.system.service;

import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.user.post.system.model.entity.SystemUserPostEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <p>
 * 用户岗位  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-16
 */
public interface ISystemUserPostService extends IService<SystemUserPostEntity> {
    /**
     * 根据用户id绑定组织id
     *
     * @param userId     用户id
     * @param postIds    岗位id
     * @param userDetail 当前用户
     * @return 是否成功
     */
    boolean bindingPostByUserId(@NonNull Long userId, @NonNull List<Long> postIds, @NonNull UserDetail userDetail);
}
