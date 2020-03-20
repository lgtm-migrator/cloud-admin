package com.hb0730.cloud.admin.server.user.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.commons.model.security.permission.SystemPermissionVO;
import com.hb0730.cloud.admin.commons.model.security.role.SystemRoleVO;
import com.hb0730.cloud.admin.server.user.system.model.entity.SystemUserEntity;
import com.hb0730.cloud.admin.server.user.system.model.vo.UserParams;
import com.hb0730.cloud.admin.server.user.system.model.vo.UserSaveVO;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
     * 保存用户信息
     *
     * @param saveVO      需要保存的用户信息
     * @param currentUser 当前用户
     * @return 是否成功
     */
    boolean save(@NonNull UserSaveVO saveVO, @NonNull UserDetail currentUser);

    /**
     * 根据账号获取用户信息
     *
     * @param username 用户账号
     * @return 登录信息
     */
    UserDetail findUserByUserName(@PathVariable String username);

    /**
     * 获取用户信息
     *
     * @param page     页数
     * @param pageSize 数量
     * @param params   参数
     * @return 分页后的用户
     */
    PageInfo getUserPage(@NonNull Integer page, @NonNull Integer pageSize, UserParams params);

    /**
     * <p>
     * 根据用户id获取用户详情
     * </p>
     *
     * @param userInfo 用户id
     * @return 用户信息(包含用户组织, 用户角色以及用户岗位)
     */
    UserSaveVO getUserInfo(@NonNull Long userInfo);

    /**
     * <p>
     * 根据用户id更新用户信息
     * </P>
     *
     * @param userId     用户id
     * @param saveVO     需要更新的信息
     * @param userDetail 当前用户
     * @return 是否成功
     */
    boolean updateById(@NonNull Long userId, UserSaveVO saveVO, @NonNull UserDetail userDetail);

    /**
     * 根据用户id删除
     *
     * @param id 用户id
     * @return 是否成功
     */
    boolean removeById(@NonNull Long id);

    /**
     * <p>
     * 根据用户id获取用户权限信息
     * </P>
     *
     * @param id 用户id
     * @return 权限信息
     */
    List<SystemPermissionVO> getPermissionByUserId(@NonNull Long id);

    /**
     * <p>
     * 根据用户id获取角色信息
     * </P>
     *
     * @param userId 用户id
     * @return 角色信息
     */
    List<SystemRoleVO> getRoleInfoByUserId(@NonNull Long userId);

}
