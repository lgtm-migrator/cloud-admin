package com.hb0730.cloud.admin.server.user.feign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import com.hb0730.cloud.admin.server.user.feign.fallback.RemoteUserDeptFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.USER_DEPT_SERVER_REQUEST;
import static com.hb0730.cloud.admin.common.util.ServerNameConstants.USER_DEPT_SERVER;

/**
 * <p>
 * 远程调用用户组织绑定
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@FeignClient(name = USER_DEPT_SERVER, url = USER_DEPT_SERVER_REQUEST, configuration = FeignConfiguration.class, fallbackFactory = RemoteUserDeptFallbackFactory.class)
public interface IRemoteUserDept extends com.hb0730.cloud.admin.api.user.dept.IRemoteUserDept {
    /**
     * <p>
     * 根据用户获组织id
     * </p>
     *
     * @param userId 用户id
     * @return 组织id
     */
    @Override
    @GetMapping("/getDeptId/{userId}")
    ResultJson getDeptByUserId(@PathVariable("userId") Long userId);

    /**
     * <p>
     * 根据用户信息绑定组织
     * </p>
     *
     * @param userId  用户id
     * @param deptIds 组织id
     * @return 是否成功
     */
    @Override
    @PostMapping("/bindingDeptId/{userId}")
    ResultJson bindingDeptByUserId(@PathVariable("userId") Long userId, @RequestBody List<Long> deptIds);
}
