package com.hb0730.cloud.admin.server.role.handler;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.hb0730.cloud.admin.common.util.RedisJetcacheUtil;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class RoleHandler {
    @CreateCache(cacheType = CacheType.REMOTE, area = RedisJetcacheUtil.RoleCache.AREA, name = RedisJetcacheUtil.RoleCache.NAME)
    private Cache<Object, Object> cache;

    /**
     * 更新缓存
     *
     * @param key 键
     * @param obj 值
     */
    public void updateCache(@NonNull String key, Object obj) {
        cache.remove(key);
        cache.put(key, obj);
    }

    /**
     * 根据key获取缓存
     *
     * @param key 键
     * @return 缓存信息
     */
    public Object getValue(@NonNull String key) {
        return cache.get(key);
    }
}
