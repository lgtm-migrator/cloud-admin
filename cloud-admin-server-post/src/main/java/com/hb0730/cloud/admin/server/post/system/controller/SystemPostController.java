package com.hb0730.cloud.admin.server.post.system.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.util.PageInfoUtil;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.post.system.model.entity.SystemPostEntity;
import com.hb0730.cloud.admin.server.post.system.model.vo.ParamsPost;
import com.hb0730.cloud.admin.server.post.system.model.vo.SystemPostVO;
import com.hb0730.cloud.admin.server.post.system.service.ISystemPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.POST_SERVER_REQUEST;

/**
 * <p>
 * 系统岗位  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-14
 */
@RestController
@RequestMapping(POST_SERVER_REQUEST)
public class SystemPostController extends AbstractBaseController<SystemPostVO> {
    @Autowired
    private ISystemPostService systemPostService;

    @PostMapping("/save")
    @Override
    public ResultJson save(@RequestBody SystemPostVO target) {
        UserDetail currentUser = getCurrentUser();
        target.setVersion(1);
        target.setCreateTime(new Date());
        target.setCreateUserId(currentUser.getId());
        SystemPostEntity entity = BeanUtils.transformFrom(target, SystemPostEntity.class);
        systemPostService.save(entity);
        return ResponseResult.resultSuccess("保存成功");
    }

    @GetMapping("/delete/{id}")
    @Override
    public ResultJson delete(@PathVariable Object id) {
        SystemPostEntity entity = systemPostService.getById(id.toString());
        if (Objects.isNull(entity)) {
            return ResponseResult.resultFall("当前数据不存在,删除失败");
        }
        entity.setUpdateTime(new Date());
        entity.setUpdateUserId(getCurrentUser().getId());
        systemPostService.updateById(entity);
        systemPostService.removeById(entity.getId());
        return ResponseResult.resultSuccess("删除成功");
    }

    @Override
    public ResultJson submit(SystemPostVO target) {
        return null;
    }

    @Override
    public ResultJson getInfo(Object id) {
        return null;
    }


    /**
     * <p>
     * 获取岗位
     * </p>
     *
     * @param page     分页页数
     * @param pageSize 数量
     * @param params   查询条件
     * @return 分页后岗位
     */
    @PostMapping("/getPage/{page}/{pageSize}")
    public ResultJson getPostsPage(@PathVariable Integer page, @PathVariable Integer pageSize, @RequestBody ParamsPost params) {
        PageHelper.startPage(page, pageSize);
        List<SystemPostEntity> entitys = systemPostService.list();
        PageInfo<SystemPostVO> pageInfo = PageInfoUtil.toBean(new PageInfo<>(entitys), SystemPostVO.class);
        return ResponseResult.resultSuccess(pageInfo);
    }

    /**
     * <p>
     * 获取岗位
     * </p>
     *
     * @return 岗位信息
     */
    @GetMapping("/posts")
    public ResultJson getPosts() {
        List<SystemPostVO> posts = systemPostService.posts();
        return ResponseResult.resultSuccess(posts);
    }

    /**
     * 更新岗位
     *
     * @param id     id
     * @param params 更新信息
     * @return 是否成功
     */
    @PostMapping("/update/{id}")
    public ResultJson updateById(@PathVariable Long id, @RequestBody SystemPostVO params) {
        SystemPostEntity entity = systemPostService.getById(id);
        if (Objects.isNull(entity)) {
            return ResponseResult.resultFall("根据id获取参数失败,请确认数据是否正确");
        }
        BeanUtils.updateProperties(params, entity);
        entity.setUpdateUserId(getCurrentUser().getId());
        entity.setUpdateTime(new Date());
        systemPostService.updateById(entity);
        return ResponseResult.resultSuccess("更新成功");
    }
}

