package com.hb0730.cloud.admin.server.router.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.cloud.admin.server.router.system.model.entity.SystemRouterEntity;

import java.util.List;

/**
 * <p>
 * 系统路由  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-21
 */
public interface ISystemRouterService extends IService<SystemRouterEntity> {

    /**
     * <p>
     * 获取全部cache
     * </p>
     *
     * @return 路由
     */
    List<SystemRouterEntity> getListByCache();

}
