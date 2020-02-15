package com.hb0730.cloud.admin.gateway.filter;

import com.hb0730.cloud.admin.common.util.GsonUtils;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <p>
 * 请求url权限校验
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
//@Configuration
public class AccessGatewayFilter implements GlobalFilter {
    private static Logger logger = LoggerFactory.getLogger(AccessGatewayFilter.class);

    /**
     * <p>
     * 1.首先网关检查token是否有效，无效直接返回401，不调用签权服务
     * 2.调用签权服务器看是否对该请求有权限，有权限进入下一个filter，没有权限返回401
     * </p>
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String method = request.getMethodValue();
        String url = request.getPath().value();
        //不需要网关签权的url
        //        //不需要网关签权的url
//        if (authService.ignoreAuthentication(url)) {
//            return chain.filter(exchange);
//        }
//        if (null != authentication) {
//            String token = authentication.substring(7);
//            if (authService.validToken(token)) {//如果验证token通过
//                ServerHttpRequest.Builder builder = request.mutate();
//                OAuth2AccessToken oAuth2AccessToken = (OAuth2AccessToken) redisUtil.get(SecurityConstants.USER_TOKEN + token);
//                List<String> btns = oAuth2AccessToken.getBtns();
//                String userJson = GsonUtils.gson2String(oAuth2AccessToken);
//                if (method.equals(HttpMethod.GET.toString())) {//如果是get请求，放行
//                    builder.header(SecurityConstants.AUTH_HEADER, userJson);
//                    return chain.filter(exchange.mutate().request(builder.build()).build());
//                }else {
//                    if (havePermission(btns, url)) {//如果有权限，放行
//                        builder.header(SecurityConstants.AUTH_HEADER, userJson);
//                        return chain.filter(exchange.mutate().request(builder.build()).build());
//                    }
//                    return unauthorized(exchange);
//                }
//            }
//        }
        return invalidtoken(exchange);
    }

    /**
     * @param btns
     * @param url
     * @return
     */
    public boolean havePermission(List<String> btns, String url) {
        PathMatcher matcher = new AntPathMatcher();
        for (String s : btns) {
            boolean match = matcher.match(s, url);
            logger.debug("我的==>      " + s + "请求:     " + url + "         ======>" + match);
            if (match) {
                return match;
            }
        }
        return Boolean.FALSE;
    }

    private Mono<Void> invalidtoken(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

    /**
     * 没有权限，返回401
     *
     * @param
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        String json = GsonUtils.gson2String(new ResultJson<>(CodeStatusEnum.AUTH_ERROR.getCode(), CodeStatusEnum.AUTH_ERROR.getMessage(), null));
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(json.getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

}
