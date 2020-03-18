package com.hb0730.cloud.admin.server.role.system.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.common.exception.BusinessException;
import com.hb0730.cloud.admin.common.exception.UserNonLoginException;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.util.PageInfoUtil;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.role.system.model.entity.SystemRoleEntity;
import com.hb0730.cloud.admin.server.role.system.model.vo.SystemRoleVO;
import com.hb0730.cloud.admin.server.role.system.service.ISystemRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.ROLE_SERVER_REQUEST;

/**
 * <p>
 * 系统角色  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
@RestController
@RequestMapping(ROLE_SERVER_REQUEST)
public class SystemRoleController extends AbstractBaseController<SystemRoleVO> {
    @Autowired
    private ISystemRoleService systemRoleService;

    @PostMapping("/save")
    @Override
    @PreAuthorize("hasAnyAuthority('role:save')")
    public ResultJson save(@RequestBody SystemRoleVO target) {
        verification(target);
        UserDetail currentUser = getCurrentUser();
        target.setCreateTime(new Date());
        target.setCreateUserId(currentUser.getId());
        target.setVersion(1);
        SystemRoleEntity entity = BeanUtils.transformFrom(target, SystemRoleEntity.class);
        systemRoleService.save(entity);
        return ResponseResult.resultSuccess("保存成功");
    }

    @Override
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('role:remove')")
    public ResultJson delete(@PathVariable Object id) {
        systemRoleService.removeById(id.toString());
        return ResponseResult.resultSuccess("修改成功");
    }

    @Override
    public ResultJson submit(SystemRoleVO target) {
        return null;
    }

    @Override
    public ResultJson getInfo(Object id) {
        return null;
    }


    @GetMapping("/findRole/role/{roleId}")
    public ResultJson findRoleById(@PathVariable("roleId") long roleId) {
        SystemRoleEntity result = systemRoleService.getById(roleId);
        return ResponseResult.resultSuccess(result);
    }

    /**
     * <p>
     * 获取集合（分页）
     * </P>
     *
     * @param page     页数
     * @param pageSize 分页数量
     * @return 计划
     */
    @GetMapping("/roles/{page}/{pageSize}")
    @PreAuthorize("hasAnyAuthority('role:query')")
    public ResultJson getListByPage(@PathVariable Integer page, @PathVariable Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<SystemRoleEntity> entityList = systemRoleService.list();
        PageInfo<SystemRoleEntity> pageInfo = new PageInfo<>(entityList);
        PageInfo<SystemRoleVO> info = PageInfoUtil.toBean(pageInfo, SystemRoleVO.class);
        return ResponseResult.resultSuccess(info);
    }

    /**
     * 获取全部角色
     *
     * @return 角色
     */
    @GetMapping("/roles")
    public ResultJson getRoles() {
        List<SystemRoleVO> roles = systemRoleService.roles();
        return ResponseResult.resultSuccess(roles);
    }

    /**
     * <p>
     * 根据id更新角色信息
     * </p>
     *
     * @param id id
     * @param vo 更新参数
     * @return 是否成功
     */
    @PostMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('role:update')")
    public ResultJson updateById(@PathVariable Long id, @RequestBody SystemRoleVO vo) {
        verification(vo);
        SystemRoleEntity entity = systemRoleService.getById(id);
        BeanUtils.updateProperties(vo, entity);
        entity.setUpdateTime(new Date());
        entity.setUpdateUserId(getCurrentUser().getId());
        systemRoleService.updateById(entity);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * <p>
     * 根据角色id获取角色信息
     * </p>
     *
     * @param ids 角色id
     * @return 角色信息
     */
    @PostMapping("/roles")
    public ResultJson getRolesByIds(@RequestBody List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResponseResult.resultSuccess(Lists.newArrayList());
        }
        List<SystemRoleEntity> entityList = systemRoleService.listByIds(ids);
        List<SystemRoleEntity> entities = entityList.parallelStream().filter(e1 -> e1.getIsEnabled() == 1).collect(Collectors.toList());
        List<SystemRoleVO> result = BeanUtils.transformFromInBatch(entities, SystemRoleVO.class);
        return ResponseResult.resultSuccess(result);
    }

    /**
     * 校验用户，角色名称与角色标识符是否存在
     *
     * @param vo 角色信息
     */
    private void verification(SystemRoleVO vo) {
        UserDetail currentUser = getCurrentUser();
        if (Objects.isNull(currentUser)) {
            throw new UserNonLoginException("当前用户未登录,操作失败");
        }
        String name = vo.getName();
        if (StringUtils.isBlank(name)) {
            throw new BusinessException("角色名称为空");
        }
        String enname = vo.getEnname();
        if (StringUtils.isBlank(enname)) {
            throw new BusinessException("角色标识为空");
        }
    }
}

