package com.hb0730.cloud.admin.server.user.post.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.user.post.system.model.entity.SystemUserPostEntity;
import com.hb0730.cloud.admin.server.user.post.system.mapper.SystemUserPostMapper;
import com.hb0730.cloud.admin.server.user.post.system.service.ISystemUserPostService;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户岗位  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-16
 */
@Service
public class SystemUserPostServiceImpl extends BaseServiceImpl<SystemUserPostMapper, SystemUserPostEntity> implements ISystemUserPostService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bindingPostByUserId(@NonNull Long userId, @NonNull List<Long> postIds, @NonNull UserDetail userDetail) {
        SystemUserPostEntity entity = new SystemUserPostEntity();
        entity.setUserId(userId);
        QueryWrapper<SystemUserPostEntity> queryWrapper = new QueryWrapper<>(entity);
        remove(queryWrapper);
        List<SystemUserPostEntity> entities = Lists.newArrayList();
        postIds.parallelStream().forEach(postId -> {
            SystemUserPostEntity e1 = new SystemUserPostEntity();
            e1.setPostId(postId);
            e1.setUserId(userId);
            e1.setIsEnabled(1);
            e1.setVersion(1);
            e1.setCreateTime(new Date());
            e1.setCreateUserId(userDetail.getUserId());
            entities.add(e1);
        });
        return saveBatch(entities);
    }
}
