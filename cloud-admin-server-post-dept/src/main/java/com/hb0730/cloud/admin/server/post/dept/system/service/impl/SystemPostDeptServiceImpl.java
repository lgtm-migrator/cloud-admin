package com.hb0730.cloud.admin.server.post.dept.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.api.feign.dept.handler.DeptHandler;
import com.hb0730.cloud.admin.commons.dept.model.vo.SystemDeptVO;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import com.hb0730.cloud.admin.server.post.dept.system.mapper.SystemPostDeptMapper;
import com.hb0730.cloud.admin.server.post.dept.system.model.entity.SystemPostDeptEntity;
import com.hb0730.cloud.admin.server.post.dept.system.service.ISystemPostDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统组织岗位  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-15
 */
@Service
public class SystemPostDeptServiceImpl extends BaseServiceImpl<SystemPostDeptMapper, SystemPostDeptEntity> implements ISystemPostDeptService {
    @Autowired
    private DeptHandler deptHandler;

    @Override
    public List<SystemDeptVO> getDeptByPostId(@NonNull Long postId) {
        SystemPostDeptEntity entity = new SystemPostDeptEntity();
        entity.setPostId(postId);
        QueryWrapper<SystemPostDeptEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemPostDeptEntity> list = list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        List<Long> deptIds = list.parallelStream().map(SystemPostDeptEntity::getDeptId).collect(Collectors.toList());
        return deptHandler.getDeptsById(deptIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bindingByPostId(@NonNull Long postId, @NonNull List<Long> deptIds, @NonNull UserDetail userDetail) {

        SystemPostDeptEntity entity = new SystemPostDeptEntity();
        entity.setPostId(postId);
        QueryWrapper<SystemPostDeptEntity> queryWrapper = new QueryWrapper<>(entity);
        remove(queryWrapper);
        List<SystemPostDeptEntity> saveEntity = Lists.newArrayList();
        deptIds.parallelStream().forEach((deptId) -> {
            SystemPostDeptEntity e1 = new SystemPostDeptEntity();
            e1.setPostId(postId);
            e1.setDeptId(deptId);
            e1.setCreateTime(new Date());
            e1.setCreateUserId(userDetail.getId());
            e1.setVersion(1);
            saveEntity.add(e1);
        });
        return saveBatch(saveEntity);
    }
}
