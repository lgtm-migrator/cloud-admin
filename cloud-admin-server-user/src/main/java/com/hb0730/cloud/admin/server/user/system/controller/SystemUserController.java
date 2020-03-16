package com.hb0730.cloud.admin.server.user.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.user.system.model.entity.SystemUserEntity;
import com.hb0730.cloud.admin.server.user.system.model.vo.SettingPasswordParams;
import com.hb0730.cloud.admin.server.user.system.model.vo.SystemUserVO;
import com.hb0730.cloud.admin.server.user.system.model.vo.UserSaveVO;
import com.hb0730.cloud.admin.server.user.system.service.ISystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.USER_SERVER_REQUEST;

/**
 * <p>
 * 系统用户  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-20
 */
@RestController
@RequestMapping(USER_SERVER_REQUEST)
public class SystemUserController extends AbstractBaseController<SystemUserVO> {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ISystemUserService systemUserService;

    //    @PostMapping("/save")
    @Override
    @Deprecated
    public ResultJson save(@RequestBody SystemUserVO target) {
        //参数校验
        if (Objects.isNull(target)) {
            return ResponseResult.resultFall("参数为空");
        }
        if (StringUtils.isBlank(target.getUsername()) && StringUtils.isBlank(target.getEmail())) {
            return ResponseResult.resultFall("用户账号或者email为空");
        }
        if (StringUtils.isBlank(target.getPassword())) {
            return ResponseResult.resultFall("用户密码为空");
        }
        //判断用户名或者邮箱是否已绑定
        SystemUserEntity userEntity = getUserEntity(target.getUsername());
        if (!Objects.isNull(userEntity)) {
            return ResponseResult.resultFall("用户账号或者邮箱已绑定");
        }
        userEntity = getUserEntity(target.getEmail());
        if (!Objects.isNull(userEntity)) {
            return ResponseResult.resultFall("用户账号或者邮箱已绑定");
        }
        //用户加密
        String password = passwordEncode(target.getPassword());
        target.setPassword(password);
        //
        UserDetail currentUser = null;
        try {
            currentUser = getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.resultFall("获取当前认证用户失败,请重新登录");
        }
        if (Objects.isNull(currentUser)) {
            return ResponseResult.resultFall("获取当前认证用户失败,请重新登录");
        }
        target.setCreateTime(new Date());
        target.setCreateUserId(currentUser.getUserId());
        SystemUserEntity entity = BeanUtils.transformFrom(target, SystemUserEntity.class);
        systemUserService.save(entity);
        return ResponseResult.resultSuccess("新增成功");
    }

    /**
     * 用户保存
     *
     * @param saveVO 用户信息
     * @return 是否成功
     */
    @PostMapping("/save")
    public ResultJson save(@RequestBody UserSaveVO saveVO) {
        boolean save = systemUserService.save(saveVO, getCurrentUser());
        return ResponseResult.resultSuccess("保存成功");
    }

    @GetMapping("/delete/{id}")
    @Override
    public ResultJson delete(@PathVariable Object id) {
        if (Objects.isNull(id)) {
            return ResponseResult.resultFall("参数id为空");
        }
        //删除清空缓存

        SystemUserEntity entity = new SystemUserEntity();
        entity.setId(Long.valueOf(id.toString()));
        entity.setIsEnabled(0);
        UpdateWrapper<SystemUserEntity> updateWrapper = new UpdateWrapper<>(entity);
        systemUserService.remove(updateWrapper);
        return ResponseResult.resultSuccess("修改成功");
    }

    @Override
    public ResultJson submit(SystemUserVO target) {
        return null;
    }

    @GetMapping("/{id}")
    @Override
    public ResultJson getInfo(@PathVariable Object id) {
        if (Objects.isNull(id)) {
            return ResponseResult.resultFall("参数id为空");
        }
        SystemUserEntity entity = systemUserService.getById(id.toString());
        SystemUserVO vo = BeanUtils.transformFrom(entity, SystemUserVO.class);
        return ResponseResult.resultSuccess(vo);
    }

    /**
     * <p>
     * 修改当前用户密码
     * </p>
     *
     * @return 是否成功
     */
    @PostMapping("/update/password/current")
    public ResultJson updatePassword(@RequestBody SettingPasswordParams passwordParams) {
        UserDetail currentUser = getCurrentUser();
        if (Objects.isNull(currentUser)) {
            return ResponseResult.result(CodeStatusEnum.NON_LOGIN, "获取当前用户失败");
        }
        SystemUserEntity entity = systemUserService.getById(currentUser.getUserId());
        if (!passwordMatches(passwordParams.getOldPassword(), entity.getPassword())) {
            return ResponseResult.resultFall("密码不正确");
        }
        String newPassword = passwordParams.getNewPassword();
        String newPassword2 = passwordParams.getNewPassword2();
        if (StringUtils.isBlank(newPassword) || StringUtils.isBlank(newPassword2)) {
            return ResponseResult.resultFall("密码不一致");
        }
        if (!passwordParams.getNewPassword().equals(passwordParams.getNewPassword2())) {
            return ResponseResult.resultFall("密码不一致");
        }
        String newPd = passwordEncode(newPassword2);
        SystemUserEntity entity1 = new SystemUserEntity();
        entity1.setId(currentUser.getUserId());
        entity1.setPassword(newPd);
        entity1.setUpdateTime(new Date());
        entity1.setUpdateUserId(currentUser.getUserId());
        systemUserService.updateById(entity1);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * <p>
     * 修改用户信息
     * </p>
     *
     * @param userVO 用户参数
     * @return 是否成功
     */
    @PostMapping("/update/userInfo")
    public ResultJson updateUserInfo(@RequestBody SystemUserVO userVO) {
        UserDetail currentUser = getCurrentUser();
        if (Objects.isNull(currentUser)) {
            return ResponseResult.result(CodeStatusEnum.NON_LOGIN, "获取当前用户失败,请重新登录");
        }
        Long id = userVO.getId();
        if (Objects.isNull(id)) {
            return ResponseResult.resultFall("获取用户id失败");
        }
        userVO.setUpdateTime(new Date());
        userVO.setUpdateUserId(currentUser.getUserId());
        SystemUserEntity entity = BeanUtils.transformFrom(userVO, SystemUserEntity.class);
        systemUserService.updateById(entity);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * <p>
     * 根据登录名查找
     * </p>
     *
     * @param login 登录账号
     * @return 用户
     */
    @GetMapping("/findUser/{login}")
    public ResultJson findUserByLogin(@PathVariable String login) {
        SystemUserEntity userEntity = getUserEntity(login);
        SystemUserVO vo = BeanUtils.transformFrom(userEntity, SystemUserVO.class);
        return ResponseResult.resultSuccess(vo);
    }

    /**
     * <p>
     * 根据用户账号或者email得到用户
     * </p>
     *
     * @param login 账号/email
     * @return 用户
     */
    private SystemUserEntity getUserEntity(String login) {
        SystemUserEntity entity = new SystemUserEntity();
        QueryWrapper<SystemUserEntity> queryWrapper = new QueryWrapper<>(entity.setUsername(login));
        SystemUserEntity userEntity = systemUserService.getOne(queryWrapper);
        if (!Objects.isNull(userEntity)) {
            return userEntity;
        }
        entity.setUsername(null);
        entity.setEmail(login);
        queryWrapper.setEntity(entity);
        userEntity = systemUserService.getOne(queryWrapper);
        if (!Objects.isNull(userEntity)) {
            return userEntity;
        }
        return null;
    }


    /**
     * <p>
     * 用户密码加密
     * </p>
     *
     * @param password 密码文明
     * @return 加密后密码
     */
    private String passwordEncode(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * <p>
     * 密码校验
     * </p>
     *
     * @param password       文明
     * @param encodePassword 加密后
     * @return 是否一致
     */
    private boolean passwordMatches(String password, String encodePassword) {
        return passwordEncoder.matches(password, encodePassword);
    }
}

