package com.hb0730.cloud.admin.server.user.system.service;

import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.user.system.model.entity.SystemUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.cloud.admin.server.user.system.model.vo.UserSaveVO;

/**
 * <p>
 * 系统用户  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-20
 */
public interface ISystemUserService extends IService<SystemUserEntity> {
    /**
     * 用户保存
     * @param saveVO 用户信息
     * @param currentUser 当前用户信息
     * @return 是否成功
     */
    boolean  save(UserSaveVO saveVO, UserDetail currentUser);
}
