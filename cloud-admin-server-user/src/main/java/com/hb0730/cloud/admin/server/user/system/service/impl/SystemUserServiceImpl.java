package com.hb0730.cloud.admin.server.user.system.service.impl;

import com.hb0730.cloud.admin.server.user.system.model.entity.SystemUserEntity;
import com.hb0730.cloud.admin.server.user.system.mapper.SystemUserMapper;
import com.hb0730.cloud.admin.server.user.system.service.ISystemUserService;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-15
 */
@Service
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUserMapper, SystemUserEntity> implements ISystemUserService {

}
