package com.hb0730.cloud.admin.server.role.system.controller;


import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.role.system.model.entity.SystemRoleEntity;
import com.hb0730.cloud.admin.server.role.system.service.ISystemRoleService;
import com.hb0730.cloud.admin.server.role.system.vo.SystemRoleVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;

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
    public ResultJson save(@RequestBody SystemRoleVO target) {
        //保存
        String name = target.getName();
        if (StringUtils.isBlank(name)) {
            return ResponseResult.resultFall("角色名称为空");
        }
        String enname = target.getEnname();
        if (StringUtils.isBlank(enname)) {
            return ResponseResult.resultFall("角色英文名称为空");
        }

        UserDetail currentUser = null;
        try {
            currentUser = getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.resultFall("获取认证用户失败,请重新登录");
        }
        if (Objects.isNull(currentUser)) {
            return ResponseResult.resultFall("获取认证用户失败,请重新登录");
        }
        target.setCreateTime(new Date());
        target.setCreateUserId(currentUser.getUserId());
        SystemRoleEntity entity = BeanUtils.transformFrom(target, SystemRoleEntity.class);
        systemRoleService.save(entity);
        return ResponseResult.resultSuccess("保存成功");
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemRoleVO target) {
        return null;
    }

    @Override
    public ResultJson getObject(Object id) {
        return null;
    }


    @GetMapping("/findRole/role/{roleId}")
    public ResultJson findRoleById(@PathVariable("roleId") long roleId) {
        SystemRoleEntity result = systemRoleService.getById(roleId);
        return ResponseResult.resultSuccess(result);
    }
}

