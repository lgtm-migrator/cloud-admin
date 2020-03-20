package com.hb0730.cloud.admin.server.post.dept.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.commons.model.security.dept.SystemDeptVO;
import com.hb0730.cloud.admin.server.post.dept.system.model.entity.SystemPostDeptEntity;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <p>
 * 系统组织岗位  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-15
 */
public interface ISystemPostDeptService extends IService<SystemPostDeptEntity> {

    /**
     * 获取组织信息
     *
     * @param postId 岗位id
     * @return 组织信息
     */
    List<SystemDeptVO> getDeptByPostId(@NonNull Long postId);

    /**
     * <p>
     * 根据岗位绑定
     * </p>
     *
     * @param postId     岗位id
     * @param deptIds    组织id
     * @param userDetail 当前用户
     * @return 是否成功
     */
    boolean bindingByPostId(@NonNull Long postId, @NonNull List<Long> deptIds, @NonNull UserDetail userDetail);
}
