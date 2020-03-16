package com.hb0730.cloud.admin.server.router.system.service.impl;

import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.util.RedisJetcacheUtil;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import com.hb0730.cloud.admin.server.router.event.SendResultJsonEvent;
import com.hb0730.cloud.admin.server.router.handler.RouterCacheHandler;
import com.hb0730.cloud.admin.server.router.system.mapper.SystemRouterMapper;
import com.hb0730.cloud.admin.server.router.system.model.entity.SystemRouterEntity;
import com.hb0730.cloud.admin.server.router.system.service.ISystemRouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


/**
 * <p>
 * 系统路由  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-21
 */
@Service
public class SystemRouterServiceImpl extends BaseServiceImpl<SystemRouterMapper, SystemRouterEntity> implements ISystemRouterService {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private RouterCacheHandler cacheHandler;

    @Override
    public boolean save(SystemRouterEntity entity) {
        boolean b = super.save(entity);
        applicationContext.publishEvent(new SendResultJsonEvent(this, ResponseResult.resultSuccess("保存路由成功"), applicationContext));
        return b;
    }

    @Override
    public boolean updateById(SystemRouterEntity entity) {
        boolean b = super.updateById(entity);
        applicationContext.publishEvent(new SendResultJsonEvent(this, ResponseResult.resultSuccess("修改路由成功"), applicationContext));
        return b;
    }

    @Override
    public List<SystemRouterEntity> getListByCache() {
        Object o = cacheHandler.getValue(RedisJetcacheUtil.RouterCache.KEY_ALL);
        // 重启 redis cache为空
        if (Objects.isNull(o)) {
            List<SystemRouterEntity> list = super.list();
            cacheHandler.updateCache(RedisJetcacheUtil.RouterCache.KEY_ALL, list);
            return list;
        }
        if (o instanceof List) {
            return BeanUtils.transformFromInBatch((List) o, SystemRouterEntity.class);
        }
        return null;
    }
}
