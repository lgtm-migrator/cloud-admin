package com.hb0730.cloud.admin.server.user.post.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.user.post.system.model.entity.SystemUserPostEntity;
import com.hb0730.cloud.admin.server.user.post.system.model.vo.SystemUserPostVO;
import com.hb0730.cloud.admin.server.user.post.system.service.ISystemUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.USER_POST_SERVER_REQUEST;

/**
 * <p>
 * 用户岗位  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-16
 */
@RestController
@RequestMapping(USER_POST_SERVER_REQUEST)
public class SystemUserPostController extends AbstractBaseController<SystemUserPostVO> {
    @Autowired
    private ISystemUserPostService systemUserPostService;

    @Override
    public ResultJson save(SystemUserPostVO target) {
        return null;
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemUserPostVO target) {
        return null;
    }

    @Override
    public ResultJson getInfo(Object id) {
        return null;
    }

    /**
     * <p>
     * 根据用户id获取岗位id
     * </p>
     *
     * @param userId 用户id
     * @return 岗位id
     */
    @GetMapping("/getPostId/{userId}")
    public ResultJson getPostByUserId(@PathVariable Long userId) {
        SystemUserPostEntity entity = new SystemUserPostEntity();
        entity.setIsEnabled(1);
        QueryWrapper<SystemUserPostEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemUserPostEntity> list = systemUserPostService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return ResponseResult.resultSuccess(list);
        }
        List<Long> depts = list.parallelStream().map(SystemUserPostEntity::getPostId).collect(Collectors.toList());
        return ResponseResult.resultSuccess(depts);
    }

    /**
     * <p>
     * 根据用户信息绑定组织
     * </p>
     *
     * @param userId  用户id
     * @param postIds 岗位id
     * @return 是否成功
     */
    @PostMapping("/bindingPostId/{userId}")
    public ResultJson bindingPostByUserId(@PathVariable Long userId, @RequestBody List<Long> postIds) {
        systemUserPostService.bindingPostByUserId(userId, postIds, getCurrentUser());
        return ResponseResult.resultSuccess("绑定成功");
    }
}

