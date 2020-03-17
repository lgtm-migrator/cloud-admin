package com.hb0730.cloud.admin.server.user.system.service;

import com.github.pagehelper.PageInfo;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.user.system.model.entity.SystemUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.cloud.admin.server.user.system.model.vo.UserParams;
import com.hb0730.cloud.admin.server.user.system.model.vo.UserSaveVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
     *
     * @param saveVO      用户信息
     * @param currentUser 当前用户信息
     * @return 是否成功
     */
    boolean save(UserSaveVO saveVO, UserDetail currentUser);

    /**
     * 获取用户信息
     *
     * @param page     页数
     * @param pageSize 数量
     * @param params   参数
     * @return 分页后的用户
     */
    PageInfo getUserPage(Integer page, Integer pageSize, UserParams params);

    /**
     * <p>
     * 获取用户详情
     * </p>
     *
     * @param userInfo 用户id
     * @return 用户信息(包含用户组织, 用户角色以及用户岗位)
     */
    UserSaveVO getUserInfo(Long userInfo);

    /**
     * <p>
     * 更新用户信息
     * </p>
     *
     * @param userId     用户id
     * @param saveVO     用户信息
     * @param userDetail 当前用户
     * @return 是否成功
     */
    boolean updateById(Long userId, UserSaveVO saveVO, UserDetail userDetail);
}
