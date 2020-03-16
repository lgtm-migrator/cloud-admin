package com.hb0730.cloud.admin.server.post.system.service;

import com.hb0730.cloud.admin.server.post.system.model.entity.SystemPostEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.cloud.admin.server.post.system.model.vo.SystemPostVO;

import java.util.List;

/**
 * <p>
 * 系统岗位  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-14
 */
public interface ISystemPostService extends IService<SystemPostEntity> {

    /**
     * 获取全部岗位信息
     *
     * @return 岗位信息
     */
    List<SystemPostVO> posts();
}
