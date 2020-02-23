package com.hb0730.cloud.admin.common.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * bean 转换时 PageHelper 失效
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class PageInfoUtil {
    /**
     * <p>
     * pageInfo to bean
     * </p>
     *
     * @param pageInfo pageinfo
     * @param clazz    转换类型
     * @param <P>
     * @param <D>
     * @return
     */
    public static <P, D> PageInfo<D> toBean(PageInfo<P> pageInfo, Class<D> clazz) {
        Page<D> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        page.setTotal(pageInfo.getTotal());
        for (P p : pageInfo.getList()) {
            D d = BeanUtils.transformFrom(p, clazz);
            page.add(d);
        }
        return new PageInfo<>(page);
    }
}
