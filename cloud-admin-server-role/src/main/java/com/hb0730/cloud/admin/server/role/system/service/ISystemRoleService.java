package com.hb0730.cloud.admin.server.role.system.service;

import com.hb0730.cloud.admin.server.role.system.model.entity.SystemRoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.cloud.admin.server.role.system.model.vo.SystemRoleVO;

import java.util.List;

/**
 * <p>
 * 系统角色  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
public interface ISystemRoleService extends IService<SystemRoleEntity> {

    /**
     * 获取全部角色
     * @return 角色
     */
    List<SystemRoleVO> roles();
}
