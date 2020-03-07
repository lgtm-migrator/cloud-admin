package com.hb0730.cloud.admin.server.permission.menu.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.common.exception.BusinessException;
import com.hb0730.cloud.admin.common.exception.NullPointerException;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.util.GsonUtils;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.commons.menu.model.vo.MenuVO;
import com.hb0730.cloud.admin.commons.menu.model.vo.SystemMenuVO;
import com.hb0730.cloud.admin.commons.permission.model.vo.SystemPermissionVO;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import com.hb0730.cloud.admin.server.permission.menu.feign.IRemoteMenu;
import com.hb0730.cloud.admin.server.permission.menu.feign.IRemotePermission;
import com.hb0730.cloud.admin.server.permission.menu.system.mapper.SystemPermissionMenuMapper;
import com.hb0730.cloud.admin.server.permission.menu.system.model.entity.SystemPermissionMenuEntity;
import com.hb0730.cloud.admin.server.permission.menu.system.model.vo.PermissionMenuListVO;
import com.hb0730.cloud.admin.server.permission.menu.system.model.vo.PermissionMenuVO;
import com.hb0730.cloud.admin.server.permission.menu.system.model.vo.SystemPermissionMenuVO;
import com.hb0730.cloud.admin.server.permission.menu.system.service.ISystemPermissionMenuService;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 菜单权限  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
@Service
public class SystemPermissionMenuServiceImpl extends BaseServiceImpl<SystemPermissionMenuMapper, SystemPermissionMenuEntity> implements ISystemPermissionMenuService {
    private Logger logger = LoggerFactory.getLogger(SystemPermissionMenuServiceImpl.class);
    @Autowired
    private IRemotePermission remotePermission;
    @Autowired
    private IRemoteMenu remoteMenu;

    @Override
    public boolean save(SystemPermissionMenuEntity entity) {
        logger.info("全局事务id ：" + RootContext.getXID());
        if (Objects.isNull(entity)) {
            throw new NullPointerException("实体为空");
        }
        if (Objects.isNull(entity.getMenuId())) {
            throw new NullPointerException("菜单id为空");
        }
        if (Objects.isNull(entity.getPermissionId())) {
            throw new NullPointerException("权限id为空");
        }
        return super.save(entity);
    }

    /**
     * <p>
     * 根据菜单id获取权限信息
     * </p>
     *
     * @param vo 参数
     * @return 权限集合
     */
    @Override
    public List<PermissionMenuVO> getPermissionMenuByMenuId(SystemPermissionMenuVO vo) {
        if (Objects.isNull(vo)) {
            throw new NullPointerException("根据菜单获取id失败,参数为空");
        }
        Long menuId = vo.getMenuId();
        if (Objects.isNull(menuId)) {
            throw new NullPointerException("根据菜单获取id失败，菜单id为空");
        }
        // 查询权限
        SystemPermissionMenuEntity entity = BeanUtils.transformFrom(vo, SystemPermissionMenuEntity.class);
        QueryWrapper<SystemPermissionMenuEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemPermissionMenuEntity> permissionMenuEntities = list(queryWrapper);
        if (!CollectionUtils.isEmpty(permissionMenuEntities)) {
            List<Long> permissionIds = permissionMenuEntities.stream().map(SystemPermissionMenuEntity::getPermissionId).collect(Collectors.toList());
//            //获取权限
            List<SystemPermissionVO> permissions = getPermissions(permissionIds);
            if (!CollectionUtils.isEmpty(permissions)) {
                SystemMenuVO menu = getMenuById(menuId);
                if (!Objects.isNull(menu)) {
                    List<PermissionMenuVO> permissionMenuVOList = Lists.newArrayList();
                    permissions.forEach(permission -> {
                        PermissionMenuVO permissionMenuVO = new PermissionMenuVO();
                        permissionMenuVO.setSystemPermissionVO(permission);
                        SystemMenuVO systemMenuVO = BeanUtils.transformFrom(menu, SystemMenuVO.class);
                        permissionMenuVO.setSystemMenuVO(systemMenuVO);
                        permissionMenuVOList.add(permissionMenuVO);
                    });
                    return permissionMenuVOList;
                }

            }

        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean unBinding(Long permissionId, Long menuId) {
        if (Objects.isNull(permissionId)) {
            throw new NullPointerException("权限id为空");
        }
        if (Objects.isNull(menuId)) {
            throw new NullPointerException("菜单id为空");
        }

        SystemPermissionMenuEntity entity = new SystemPermissionMenuEntity();
        entity.setPermissionId(permissionId);
        entity.setMenuId(menuId);
        QueryWrapper<SystemPermissionMenuEntity> queryWrapper = new QueryWrapper<>(entity);
        return remove(queryWrapper);
    }

    @Override
    public List<PermissionMenuListVO> getAllPermissionMenu() {
        List<PermissionMenuListVO> permissionMenus = Lists.newArrayList();
        List<PermissionMenuListVO> menus = getMenusByParentId(0L);
        if (CollectionUtils.isEmpty(menus)) {
            return permissionMenus;
        }
        menus.forEach(menu -> {
            List<PermissionMenuListVO> childrens = Lists.newArrayList();
            PermissionMenuListVO childes = getChildes(menu, childrens);
            permissionMenus.add(childes);
        });
        return permissionMenus;
    }

    /**
     * <p>
     * 获取子菜单权限
     * </p>
     *
     * @param vo    父菜单权限
     * @param menus 子集
     * @return 菜单权限
     */
    private PermissionMenuListVO getChildes(PermissionMenuListVO vo, List<PermissionMenuListVO> menus) {

        if (vo.getType() == 0) {
            //获取菜单
            List<PermissionMenuListVO> childes = getMenusByParentId(vo.getId());
            List<PermissionMenuListVO> permissions = getPermissionByMenuId(vo.getId());
            List<PermissionMenuListVO> childrens = vo.getChildren();
            if (CollectionUtils.isEmpty(childrens)) {
                vo.setChildren(menus);
            } else {
                vo.getChildren().addAll(menus);
            }
            if (!CollectionUtils.isEmpty(permissions)) {
                vo.getChildren().addAll(permissions);
            }
            if (!CollectionUtils.isEmpty(childes)) {
                childes.forEach(children -> {
                    List<PermissionMenuListVO> voArrayList = Lists.newArrayList();
                    PermissionMenuListVO listVO = getChildes(children, voArrayList);
                    menus.add(listVO);
                });
            }
        }
        return vo;
    }

    /**
     * <p>
     * 根据菜单id获取权限
     * </p>
     *
     * @param id 菜单id
     * @return 权限
     */
    private List<PermissionMenuListVO> getPermissionByMenuId(@NonNull Long id) {
        SystemPermissionMenuEntity entity = new SystemPermissionMenuEntity();
        entity.setMenuId(id);
        QueryWrapper<SystemPermissionMenuEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemPermissionMenuEntity> permissionMenuEntities = list(queryWrapper);
        if (CollectionUtils.isEmpty(permissionMenuEntities)) {
            return null;
        }
        List<Long> permissionIds = permissionMenuEntities.stream().map(SystemPermissionMenuEntity::getPermissionId).collect(Collectors.toList());
        List<SystemPermissionVO> permissions = getPermissions(permissionIds);
        if (CollectionUtils.isEmpty(permissions)) {
            return null;
        }
        List<PermissionMenuListVO> permissionMenus = Lists.newArrayList();
        permissions.forEach(permission->{
            PermissionMenuListVO vo = new PermissionMenuListVO();
            vo.setType(1);
            vo.setName(permission.getName());
            vo.setId(permission.getId());
            permissionMenus.add(vo);
        });
        return permissionMenus;
    }

    /**
     * <p>
     * 根据父id获取菜单
     * </P>
     *
     * @param id 父id
     * @return 菜单权限vo
     */
    private List<PermissionMenuListVO> getMenusByParentId(@NonNull Long id) {
        ResultJson result = remoteMenu.getMenusByParentId(id);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getErrCode())) {
            throw new BusinessException(result.getErrorMessage());
        }
        List<SystemMenuVO> vos = GsonUtils.json2List(JSONArray.toJSONString(result.getData()), SystemMenuVO.class);
        if (CollectionUtils.isEmpty(vos)) {
            return null;
        }
        List<PermissionMenuListVO> permissionMenus = Lists.newArrayList();
        vos.forEach(menu -> {
            PermissionMenuListVO vo = new PermissionMenuListVO();
            vo.setType(0);
            vo.setName(menu.getName());
            vo.setId(menu.getId());
            permissionMenus.add(vo);
        });
        return permissionMenus;
    }

    /**
     * <p>
     * 根据id获取权限
     * </P>
     *
     * @param permissionIds id集合
     * @return 权限
     */
    private List<SystemPermissionVO> getPermissions(List<Long> permissionIds) {
        if (CollectionUtils.isEmpty(permissionIds)) {
            return null;
        }
        ResultJson result = remotePermission.getPermissionByIds(permissionIds);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getErrCode())) {
            throw new BusinessException(result.getErrorMessage());
        }
        return GsonUtils.json2List(JSONArray.toJSONString(result.getData()), SystemPermissionVO.class);
    }

    /**
     * <p>
     * 根据id获取菜单
     * </P>
     *
     * @param menuId 菜单id
     * @return 菜单
     */
    private SystemMenuVO getMenuById(Long menuId) {
        if (Objects.isNull(menuId)) {
            return null;
        }
        ResultJson result = remoteMenu.getMenuById(menuId);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getErrCode())) {
            throw new BusinessException(result.getErrorMessage());
        }
        return GsonUtils.gsonToBean(GsonUtils.gson2String(result.getData()), SystemMenuVO.class);
    }
}
