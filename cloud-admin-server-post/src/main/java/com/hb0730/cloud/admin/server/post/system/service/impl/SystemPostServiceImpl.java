package com.hb0730.cloud.admin.server.post.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.util.RedisJetcacheUtil;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import com.hb0730.cloud.admin.server.post.event.PostEvent;
import com.hb0730.cloud.admin.server.post.handler.PostHandler;
import com.hb0730.cloud.admin.server.post.system.mapper.SystemPostMapper;
import com.hb0730.cloud.admin.server.post.system.model.entity.SystemPostEntity;
import com.hb0730.cloud.admin.server.post.system.model.vo.SystemPostVO;
import com.hb0730.cloud.admin.server.post.system.service.ISystemPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统岗位  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-14
 */
@Service
public class SystemPostServiceImpl extends BaseServiceImpl<SystemPostMapper, SystemPostEntity> implements ISystemPostService {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private PostHandler postHandler;

    @Override
    public boolean updateById(SystemPostEntity entity) {
        boolean b = super.updateById(entity);
        applicationContext.publishEvent(new PostEvent(this, applicationContext));
        return b;
    }

    @Override
    public boolean removeById(Serializable id) {
        boolean b = super.removeById(id);
        applicationContext.publishEvent(new PostEvent(this, applicationContext));
        return b;
    }

    @Override
    public boolean save(SystemPostEntity entity) {
        boolean b = super.save(entity);
        applicationContext.publishEvent(new PostEvent(this, applicationContext));
        return b;
    }

    @Override
    public List<SystemPostVO> posts() {
        Object value = postHandler.getValue(RedisJetcacheUtil.PostCache.KEY_ALL);
        if (Objects.isNull(value)) {
            SystemPostEntity entity = new SystemPostEntity();
            entity.setIsEnabled(1);
            QueryWrapper<SystemPostEntity> queryWrapper = new QueryWrapper<>(entity);
            List<SystemPostEntity> list = list(queryWrapper);
            postHandler.updateCache(RedisJetcacheUtil.PostCache.KEY_ALL, list);
            return BeanUtils.transformFromInBatch(list, SystemPostVO.class);
        } else {
            return BeanUtils.transformFromInBatch((List) value, SystemPostVO.class);
        }
    }
}
