package com.hb0730.cloud.admin.server.post.dept.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.post.dept.system.model.entity.SystemPostDeptEntity;
import com.hb0730.cloud.admin.server.post.dept.system.model.vo.SystemPostDeptVO;
import com.hb0730.cloud.admin.server.post.dept.system.service.ISystemPostDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;

import java.util.List;
import java.util.stream.Collectors;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.POST_DEPT_SERVER_REQUEST;

/**
 * <p>
 * 系统组织岗位  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-15
 */
@RestController
@RequestMapping(POST_DEPT_SERVER_REQUEST)
public class SystemPostDeptController extends AbstractBaseController<SystemPostDeptVO> {
    @Autowired
    private ISystemPostDeptService systemPostDeptService;

    @Override
    public ResultJson save(SystemPostDeptVO target) {
        return null;
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemPostDeptVO target) {
        return null;
    }

    @Override
    public ResultJson getInfo(Object id) {
        return null;
    }


    /**
     * <p>
     * 获取已绑定岗位的组织
     * </p>
     *
     * @param postId 岗位id
     * @return 组织
     */
    @GetMapping("/getDeptByPostId/{postId}")
    public ResultJson getDeptBinding(@PathVariable Long postId) {
        return ResponseResult.resultSuccess(systemPostDeptService.getDeptByPostId(postId));
    }

    /**
     * <p>
     * 获取已绑定岗位组织id
     * </p>
     *
     * @param postId 岗位id
     * @return 组织
     */
    @GetMapping("/getDeptIdByPostId/{postId}")
    public ResultJson getDeptIdBinding(@PathVariable Long postId) {
        SystemPostDeptEntity entity = new SystemPostDeptEntity();
        entity.setPostId(postId);
        QueryWrapper<SystemPostDeptEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemPostDeptEntity> entityList = systemPostDeptService.list(queryWrapper);
        if (CollectionUtils.isEmpty(entityList)) {
            return ResponseResult.resultSuccess(Lists.newArrayList());
        }
        List<Long> deptIds = entityList.parallelStream().map(SystemPostDeptEntity::getDeptId).collect(Collectors.toList());
        return ResponseResult.resultSuccess(deptIds);
    }

    /**
     * <p>
     * 绑定
     * </p>
     *
     * @param postId  岗位id
     * @param deptIds 组织id
     * @return 是否成功
     */
    @PostMapping("/binding/{postId}")
    public ResultJson bindingByPostId(@PathVariable Long postId, @RequestBody List<Long> deptIds) {
        systemPostDeptService.bindingByPostId(postId, deptIds, getCurrentUser());
        return ResponseResult.resultSuccess("保存成功");
    }
}

