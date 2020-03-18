package com.hb0730.cloud.admin.server.user.handler;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.common.exception.BusinessException;
import com.hb0730.cloud.admin.common.util.GsonUtils;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.commons.dept.model.vo.SystemDeptVO;
import com.hb0730.cloud.admin.commons.user.dept.model.vo.UserDeptParamsVO;
import com.hb0730.cloud.admin.server.user.feign.IRemoteDept;
import com.hb0730.cloud.admin.server.user.feign.IRemoteUserDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class DeptHandler {

    @Autowired
    private IRemoteDept remoteDept;
    @Autowired
    private IRemoteUserDept remoteUserDept;

    /**
     * 根据id获取组织
     *
     * @param id id
     * @return 组织信息
     */
    public SystemDeptVO getDeptById(@NonNull Long id) {
        List<Long> ids = Lists.newArrayList(id);
        ResultJson result = remoteDept.getDeptByIds(ids);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getErrCode())) {
            throw new BusinessException(result.getData().toString());
        }
        List<SystemDeptVO> resultList = GsonUtils.json2List(GsonUtils.json2String(result.getData()), SystemDeptVO.class);
        if (CollectionUtils.isEmpty(resultList)) {
            return null;
        }
        return resultList.get(0);

    }

    /**
     * <p>
     * 根据用户id获取组织id
     * </p>
     *
     * @param id 用户id
     * @return 组织id
     */
    public Long getDeptIdByUserId(@NonNull Long id) {
        ResultJson result = remoteUserDept.getDeptByUserId(id);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getErrCode())) {
            throw new BusinessException(result.getData().toString());
        }
        List<Long> deptIds = GsonUtils.json2List(GsonUtils.json2String(result.getData()), Long.class);
        if (CollectionUtils.isEmpty(deptIds)) {
            return null;
        }
        return deptIds.get(0);
    }

    /**
     * <p>
     * 根据用户id获取组织信息
     * </p>
     *
     * @param userId 用户id
     * @return 组织信息
     */
    public SystemDeptVO getDeptByUserId(@NonNull Long userId) {
        Long deptId = getDeptIdByUserId(userId);
        return getDeptById(deptId);
    }

    /**
     * <p>
     * 分页获取指定组织用户
     * </p>
     *
     * @param page     页数
     * @param pageSize 属性
     * @param params   组织过滤条件
     * @return 分页后的组织
     */
    public PageInfo getPage(@NonNull Integer page, @NonNull Integer pageSize, UserDeptParamsVO params) {
        ResultJson result = remoteUserDept.getPage(page, pageSize, params);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getErrCode())) {
            throw new BusinessException(result.getData().toString());
        }
        String resultJson = GsonUtils.gson2String(result.getData());
        return GsonUtils.json2Bean(resultJson, PageInfo.class);
    }

    /**
     * 根据用户删除绑定
     *
     * @param id 用户id
     */
    public void removeByUserId(@NonNull Long id) {
        ResultJson resultJson = remoteUserDept.removeByUserId(id);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(resultJson.getErrCode())) {
            throw new BusinessException(resultJson.getData().toString());
        }
    }


    /**
     * 组织用户绑定
     *
     * @param userId 用户id
     * @param deptId 组织id
     */
    public void bindingDeptByUserId(@NonNull Long userId, @NonNull List<Long> deptId) {
        ResultJson result = remoteUserDept.bindingDeptByUserId(userId, deptId);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getErrCode())) {
            throw new BusinessException(result.getData().toString());
        }
    }
}
