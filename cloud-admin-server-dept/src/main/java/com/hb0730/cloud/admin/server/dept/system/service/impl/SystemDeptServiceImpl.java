package com.hb0730.cloud.admin.server.dept.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import com.hb0730.cloud.admin.server.dept.system.mapper.SystemDeptMapper;
import com.hb0730.cloud.admin.server.dept.system.model.entity.SystemDeptEntity;
import com.hb0730.cloud.admin.server.dept.system.model.vo.DeptTreeVO;
import com.hb0730.cloud.admin.server.dept.system.service.ISystemDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 系统部门  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-08
 */
@Service
public class SystemDeptServiceImpl extends BaseServiceImpl<SystemDeptMapper, SystemDeptEntity> implements ISystemDeptService {
    @Autowired
    private SystemDeptMapper mapper;

    @Override
    public List<DeptTreeVO> getTree() {
        List<DeptTreeVO> tree = Lists.newArrayList();
        List<DeptTreeVO> parents = getDeptByParentId(-1L);
        if (!CollectionUtils.isEmpty(parents)) {
            parents.forEach(parent -> {
                List<DeptTreeVO> childrens = Lists.newArrayList();
                DeptTreeVO childes = getChildesForDept(parent, childrens);
                tree.add(childes);
            });
        }
        return tree;
    }


    /**
     * 获取子集
     *
     * @param dept     组织数
     * @param deptDtOs 组织集合
     * @return 组织数
     */
    private DeptTreeVO getChildesForDept(DeptTreeVO dept, List<DeptTreeVO> deptDtOs) {

        SystemDeptEntity entity = new SystemDeptEntity();
        entity.setParentId(dept.getId());
        //获取子集
        QueryWrapper<SystemDeptEntity> query = new QueryWrapper<>(entity);
        List<SystemDeptEntity> childes = mapper.selectList(query);
        dept.setChildren(deptDtOs);
        if (!CollectionUtils.isEmpty(childes)) {
            //获取下一子集
            childes.stream().forEach(childed -> {
                DeptTreeVO treeDto = BeanUtils.transformFrom(childed, DeptTreeVO.class);
                List<DeptTreeVO> deptDtoList = Lists.newArrayList();
                DeptTreeVO childesForDept = getChildesForDept(treeDto, deptDtoList);
                deptDtOs.add(childesForDept);
            });
        }

        return dept;
    }

    /**
     * <p>
     * 根据父id获取组织
     * </p>
     *
     * @param parentId 父id
     * @return 组织
     */
    private List<DeptTreeVO> getDeptByParentId(@NonNull Long parentId) {
        SystemDeptEntity entity = new SystemDeptEntity();
        entity.setParentId(parentId);
        QueryWrapper<SystemDeptEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemDeptEntity> systemDeptEntities = mapper.selectList(queryWrapper);
        return BeanUtils.transformFromInBatch(systemDeptEntities, DeptTreeVO.class);
    }
}
