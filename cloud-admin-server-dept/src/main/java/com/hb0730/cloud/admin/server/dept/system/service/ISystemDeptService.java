package com.hb0730.cloud.admin.server.dept.system.service;

import com.hb0730.cloud.admin.server.dept.system.model.entity.SystemDeptEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.cloud.admin.server.dept.system.model.vo.DeptTreeVO;
import com.hb0730.cloud.admin.server.dept.system.model.vo.SystemDeptVO;

import java.util.List;

/**
 * <p>
 * 系统部门  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-08
 */
public interface ISystemDeptService extends IService<SystemDeptEntity> {

    /**
     * <p>
     * 获取树形
     * </p>
     *
     * @return 树形
     */
    List<DeptTreeVO> getTree();
}
