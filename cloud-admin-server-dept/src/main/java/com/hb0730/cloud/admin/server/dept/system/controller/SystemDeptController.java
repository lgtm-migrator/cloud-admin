package com.hb0730.cloud.admin.server.dept.system.controller;


import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.dept.system.model.entity.SystemDeptEntity;
import com.hb0730.cloud.admin.server.dept.system.model.vo.DeptInfoVO;
import com.hb0730.cloud.admin.server.dept.system.model.vo.SystemDeptVO;
import com.hb0730.cloud.admin.server.dept.system.service.ISystemDeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.DEPT_SERVER_REQUEST;

/**
 * <p>
 * 系统部门  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-08
 */
@RestController
@RequestMapping(DEPT_SERVER_REQUEST)
public class SystemDeptController extends AbstractBaseController<SystemDeptVO> {
    private static final String F = "-1";
    @Autowired
    private ISystemDeptService systemDeptService;

    @Override
    @Deprecated
    public ResultJson save(SystemDeptVO target) {
        return null;
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemDeptVO target) {
        return null;
    }

    @GetMapping("/info/{id}")
    @Override
    public ResultJson getInfo(@PathVariable("id") Object id) {
        SystemDeptEntity info = systemDeptService.getById(id.toString());
        return ResponseResult.resultSuccess(BeanUtils.transformFrom(info, SystemDeptVO.class));
    }

    /**
     * <p>
     * 获取树形
     * </p>
     *
     * @return 树形组织
     */
    @GetMapping("/tree")
    public ResultJson getTree() {
        return ResponseResult.resultSuccess(systemDeptService.getTree());
    }

    /**
     * 新增
     *
     * @param vo 组织info
     * @return 是否成功
     */
    @PostMapping("/save")
    public ResultJson save(@RequestBody DeptInfoVO vo) {
        UserDetail currentUser = getCurrentUser();
        if (Objects.isNull(currentUser)) {
            return ResponseResult.result(CodeStatusEnum.NON_LOGIN, "用户为登录");
        }
        String number = vo.getNumber();
        if (StringUtils.isBlank(number)) {
            return ResponseResult.resultFall("组织编码为空");
        }
        String name = vo.getName();
        if (StringUtils.isBlank(name)) {
            return ResponseResult.resultFall("组织名称为空");
        }
        if (Objects.isNull(vo.getParentId())) {
            return ResponseResult.resultFall("父组织id为空");
        }
        vo.setCreateTime(new Date());
        vo.setCreateUserId(currentUser.getId());
        vo.setVersion(1);
        SystemDeptEntity entity = BeanUtils.transformFrom(vo, SystemDeptEntity.class);
        systemDeptService.save(entity);
        return ResponseResult.resultSuccess("新增成功");
    }

    /**
     * 更新组织
     *
     * @param vo 组织信息
     * @param id 组织id
     * @return 是否成功
     */
    @PostMapping("/update/{id}")
    public ResultJson update(@RequestBody SystemDeptVO vo, @PathVariable("id") Long id) {
        Long parentId = vo.getParentId();
        if (Objects.isNull(parentId)) {
            ResponseResult.resultFall("父id为空");
        }
        SystemDeptEntity entity = systemDeptService.getById(id);
        BeanUtils.updateProperties(vo, entity);
        systemDeptService.updateById(entity);
        entity.setUpdateTime(new Date());
        entity.setUpdateUserId(getCurrentUser().getId());
        return ResponseResult.resultSuccess("");
    }

    /**
     * 根据id获取组织信息
     *
     * @param ids id集合
     * @return 组织信息
     */
    @PostMapping("/getDeptById")
    public ResultJson getDeptByIds(@RequestBody List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResponseResult.resultSuccess(Lists.newArrayList());
        }
        List<SystemDeptEntity> entity = systemDeptService.listByIds(ids);
        List<SystemDeptVO> vos = BeanUtils.transformFromInBatch(entity, SystemDeptVO.class);
        return ResponseResult.resultSuccess(vos);
    }
}

