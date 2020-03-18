package com.hb0730.cloud.admin.server.user.feign;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import com.hb0730.cloud.admin.server.user.feign.fallback.RemoteDeptFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.DEPT_SERVER_REQUEST;
import static com.hb0730.cloud.admin.common.util.ServerNameConstants.DEPT_SERVER;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@FeignClient(name = DEPT_SERVER, path = DEPT_SERVER_REQUEST, configuration = FeignConfiguration.class, fallbackFactory = RemoteDeptFallbackFactory.class)
public interface IRemoteDept extends com.hb0730.cloud.admin.api.dept.IRemoteDept {

    /**
     * 根据id获取组织
     *
     * @param ids id集
     * @return 组织
     */
    @PostMapping("/getDeptById")
    @Override
    ResultJson getDeptByIds(@RequestBody List<Long> ids);
}
