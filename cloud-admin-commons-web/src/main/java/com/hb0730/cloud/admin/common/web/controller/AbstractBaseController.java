package com.hb0730.cloud.admin.common.web.controller;

import com.hb0730.cloud.admin.common.web.response.ResultJson;

/**
 * <p>
 * 通用 Controller
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public abstract class AbstractBaseController<T> {
    /**
     * 保存
     *
     * @param target 目标
     * @return ResultJson
     */
    public abstract ResultJson save(T target);

    /**
     * 删除
     *
     * @param id 目标
     * @return ResultJson
     */
    public abstract ResultJson delete(Object id);

    /**
     * 提交
     *
     * @param target 目标
     * @return ResultJson
     */
    public abstract ResultJson submit(T target);

    /**
     * 查询
     *
     * @param id 目标
     * @return ResultJson
     */
    public abstract ResultJson gitObject(Object id);
}
