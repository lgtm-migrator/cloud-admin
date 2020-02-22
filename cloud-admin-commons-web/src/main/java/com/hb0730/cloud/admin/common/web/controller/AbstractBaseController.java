package com.hb0730.cloud.admin.common.web.controller;

import com.hb0730.cloud.admin.common.exception.Oauth2Exception;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.SecurityContextUtils;
import com.hb0730.cloud.admin.common.web.vo.BaseDomainVO;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;

/**
 * <p>
 * 通用 Controller
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public abstract class AbstractBaseController<T extends BaseDomainVO> {
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
    public abstract ResultJson getObject(Object id);

    /**
     * <p>
     * 获取当前用户
     * </P>
     *
     * @return 用户
     */
    public UserDetail getCurrentUser() {
        try {
            return SecurityContextUtils.getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Oauth2Exception("获取当前用户失败,请重新登录");
        }
    }
}
