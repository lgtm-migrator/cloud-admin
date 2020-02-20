package com.hb0730.cloud.admin.server.oauth2.controller;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.google.common.collect.Maps;
import com.hb0730.cloud.admin.common.util.GsonUtils;
import com.hb0730.cloud.admin.common.util.JetcacheRedisConstants;
import com.hb0730.cloud.admin.common.util.OkHttpClientUtil;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.oauth2.model.UserDetail;
import com.hb0730.cloud.admin.server.oauth2.model.dto.LoginParams;
import com.hb0730.cloud.admin.server.oauth2.model.vo.LoginSuccessResult;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.OAUTH2_SERVER_REQUEST;

/**
 * <p>
 * 用户登录
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(OAUTH2_SERVER_REQUEST)
public class LoginController {
    @Value("${cloud.admin.oauth2.grantType}")
    private String grantType;
    @Value("${cloud.admin.oauth2.clientId}")
    private String clientId;
    @Value("${cloud.admin.oauth2.clientSecret}")
    private String clientSecret;
    @Value("${cloud.admin.oauth2.loginUrl}")
    private String loginUrl;
    @Autowired
    private UserDetailsService userDetailServer;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private TokenStore tokenStore;
    @CreateCache(cacheType = CacheType.REMOTE, area = JetcacheRedisConstants.REDIS_JETCACHE_AREA, name = JetcacheRedisConstants.REDIS_JETCACHE_NAME_USER)
    private Cache<Long, LoginSuccessResult> userCache;

    /**
     * 用户登录
     *
     * @param params 登录参数
     * @return {@link LoginSuccessResult}
     * @see {@link org.springframework.security.oauth2.provider.endpoint.TokenEndpoint#postAccessToken}
     */
    @PostMapping("/user/login")
    public ResultJson login(@RequestBody LoginParams params) throws IOException {
        UserDetail userDetails = (UserDetail) userDetailServer.loadUserByUsername(params.getUserName());
        if (Objects.isNull(userDetails)) {
            return ResponseResult.resultFall("账号或者密码不正确");
        }
        String password = userDetails.getPassword();
        boolean matches = passwordEncoder.matches(params.getPassword(), password);
        if (!matches) {
            return ResponseResult.resultFall("账号或者密码不正确");
        }
        HashMap<String, String> paramMap = Maps.newHashMap();
        paramMap.put("username", params.getUserName());
        paramMap.put("password", params.getPassword());
        paramMap.put("grant_type", grantType);
        paramMap.put("client_id", clientId);
        paramMap.put("client_secret", clientSecret);
        Response response = OkHttpClientUtil.getInstance().postData(loginUrl, paramMap);
        String resultJson = Objects.requireNonNull(response.body()).string();
        System.out.println(resultJson);
        Map<String, Object> map = GsonUtils.json2Maps(resultJson);
        String token = String.valueOf(map.get("access_token"));
        LoginSuccessResult result = new LoginSuccessResult();
        result.setAccessToken(token);
        String refreshToken = String.valueOf(map.get("refresh_token"));
        result.setRefreshToken(refreshToken);
        return ResponseResult.resultSuccess(result);
    }

    /**
     * <p>
     * 用户信息
     * </p>
     *
     * @param request 请求
     * @return 用户信息
     */
    @GetMapping(value = "/user/info")
    public ResultJson userInfo(HttpServletRequest request) {
        // 获取 token
        String token = request.getParameter("access_token");
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginSuccessResult result = new LoginSuccessResult();
        result.setRefreshToken(token);
        result.setUserName(authentication.getName());
        return ResponseResult.resultSuccess(result);
    }

    /**
     * <p>
     * 登出
     * </p>
     *
     * @param request 请求
     * @return 用户信息
     */
    @GetMapping("/user/logout")
    public ResultJson logout(HttpServletRequest request) {
        String token = request.getParameter("access_token");
        // 删除 token 以注销
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return ResponseResult.resultSuccess("注销成功");
    }
}
