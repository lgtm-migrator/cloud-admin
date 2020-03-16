package com.hb0730.cloud.admin.server.user.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.cloud.admin.common.exception.BusinessException;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.user.system.model.entity.SystemUserEntity;
import com.hb0730.cloud.admin.server.user.system.mapper.SystemUserMapper;
import com.hb0730.cloud.admin.server.user.system.model.vo.UserSaveVO;
import com.hb0730.cloud.admin.server.user.system.service.ISystemUserService;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 系统用户  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-20
 */
@Service
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUserMapper, SystemUserEntity> implements ISystemUserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(UserSaveVO saveVO, UserDetail userDetail) {
        //参数校验
        if (Objects.isNull(saveVO)) {
            throw new BusinessException("参数为空");
        }
        if (StringUtils.isBlank(saveVO.getUsername()) && StringUtils.isBlank(saveVO.getEmail())) {
            throw new BusinessException("用户账号或者email为空");
        }
        if (StringUtils.isBlank(saveVO.getPassword())) {
            throw new BusinessException("用户密码为空");
        }
        //判断用户名或者邮箱是否已绑定
        SystemUserEntity userEntity = getUserEntity(saveVO.getUsername());
        if (!Objects.isNull(userEntity)) {
            throw new BusinessException("用户账号或者邮箱已绑定");
        }
        userEntity = getUserEntity(saveVO.getEmail());
        if (!Objects.isNull(userEntity)) {
            throw new BusinessException("用户账号或者邮箱已绑定");
        }
        //用户加密
        String password = passwordEncode(saveVO.getPassword());
        saveVO.setPassword(password);
        saveVO.setCreateTime(new Date());
        saveVO.setCreateUserId(userDetail.getUserId());
        SystemUserEntity entity = BeanUtils.transformFrom(saveVO, SystemUserEntity.class);
        boolean save = save(entity);

        return false;
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
        SystemUserEntity userEntity = getOne(queryWrapper);
        if (!Objects.isNull(userEntity)) {
            return userEntity;
        }
        entity.setUsername(null);
        entity.setEmail(login);
        queryWrapper.setEntity(entity);
        userEntity =getOne(queryWrapper);
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
}
