package com.hb0730.cloud.admin.api.feign.user.post.remote;

import com.hb0730.cloud.admin.api.feign.user.post.remote.fallback.RemoteUserPostFallbackFactory;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.USER_POST_SERVER_REQUEST;
import static com.hb0730.cloud.admin.common.util.ServerNameConstants.USER_POST_SERVER;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@FeignClient(value = USER_POST_SERVER, path = USER_POST_SERVER_REQUEST, configuration = FeignConfiguration.class, fallbackFactory = RemoteUserPostFallbackFactory.class)
public interface IRemoteUserPost {
    /**
     * <p>
     * 根据用户id获取岗位id
     * </p>
     *
     * @param userId 用户id
     * @return 岗位id
     */
    @GetMapping("/getPostId/{userId}")
    ResultJson getPostByUserId(@PathVariable("userId") Long userId);

    /**
     * <p>
     * 根据用户信息绑定组织
     * </p>
     *
     * @param userId  用户id
     * @param postIds 岗位id
     * @return 是否成功
     */
    @PostMapping("/bindingPostId/{userId}")
    ResultJson bindingPostByUserId(@PathVariable("userId") Long userId, @RequestBody List<Long> postIds);

    /**
     * 根据用户删除
     *
     * @param userId 用户id
     * @return 是否成功
     */
    @GetMapping("/delete/user/{id}")
    ResultJson removeByUserId(@PathVariable("id") Long userId);
}
