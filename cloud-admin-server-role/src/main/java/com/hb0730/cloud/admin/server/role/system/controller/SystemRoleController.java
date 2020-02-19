package com.hb0730.cloud.admin.server.role.system.controller;


import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.role.system.model.entity.SystemRoleEntity;
import com.hb0730.cloud.admin.server.role.system.service.ISystemRoleService;
import com.hb0730.cloud.admin.server.role.utils.SecurityContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class SystemRoleController extends AbstractBaseController<SystemRoleEntity> {
    @Autowired
    private ISystemRoleService systemRoleService;

    @PostMapping("/save")
    @Override
    public ResultJson save(@RequestBody SystemRoleEntity target) {
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
            currentUser = SecurityContextUtils.getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.resultFall("获取认证用户失败,请重新登录");
        }
        if (Objects.isNull(currentUser)) {
            return ResponseResult.resultFall("获取认证用户失败,请重新登录");
        }
        target.setCreateTime(new Date());
        target.setCreateUserId(currentUser.getUserId());
        systemRoleService.save(target);
        return ResponseResult.resultSuccess("保存成功");
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemRoleEntity target) {
        return null;
    }

    @Override
    public ResultJson gitObject(Object id) {
        return null;
    }

}

