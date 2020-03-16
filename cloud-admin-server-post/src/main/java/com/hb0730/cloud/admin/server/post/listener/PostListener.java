package com.hb0730.cloud.admin.server.post.listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.cloud.admin.common.util.RedisJetcacheUtil;
import com.hb0730.cloud.admin.server.post.event.PostEvent;
import com.hb0730.cloud.admin.server.post.handler.PostHandler;
import com.hb0730.cloud.admin.server.post.system.model.entity.SystemPostEntity;
import com.hb0730.cloud.admin.server.post.system.service.ISystemPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class PostListener {
    @Autowired
    private ISystemPostService systemPostService;
    @Autowired
    private PostHandler handler;

    @EventListener
    @Async
    public void onApplicationEvent(PostEvent event) {
        SystemPostEntity entity = new SystemPostEntity();
        entity.setIsEnabled(1);
        QueryWrapper<SystemPostEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemPostEntity> list = systemPostService.list(queryWrapper);
        handler.updateCache(RedisJetcacheUtil.PostCache.KEY_ALL, list);
    }
}
