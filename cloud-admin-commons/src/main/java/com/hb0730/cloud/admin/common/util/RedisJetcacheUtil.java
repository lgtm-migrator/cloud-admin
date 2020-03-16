package com.hb0730.cloud.admin.common.util;

/**
 * <p>
 * redis缓存名称命名
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RedisJetcacheUtil {
    /**
     * 路由缓存
     */
    public static class RouterCache {
        /**
         * area
         */
        public static final String AREA = "cloud-admin";
        /**
         * name
         */
        public static final String NAME = "router";

        /**
         * 更新全部
         */
        public static final String KEY_ALL = "all";
    }

    /**
     * 岗位缓存
     */
    public static class PostCache {
        /**
         * area
         */
        public static final String AREA = "cloud-admin";
        /**
         * name
         */
        public static final String NAME = "post";

        /**
         * 更新全部
         */
        public static final String KEY_ALL = "all";
    }
}
