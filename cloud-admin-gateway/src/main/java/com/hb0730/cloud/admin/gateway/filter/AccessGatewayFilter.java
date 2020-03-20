package com.hb0730.cloud.admin.gateway.filter;

import com.google.common.base.Charsets;
import com.hb0730.cloud.admin.common.util.GsonUtils;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
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
@Configuration
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
        try {
            //获取response的 返回数据
            ServerHttpResponse originalResponse = exchange.getResponse();
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            HttpStatus statusCode = originalResponse.getStatusCode();

            ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                @Override
                public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                    // 判断服务返回的数据类型进行拦截，根据自己的业务进行修改
                    if (MediaType.APPLICATION_JSON.isCompatibleWith(getDelegate().getHeaders().getContentType())) {
                        Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                        return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                            DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                            DataBuffer join = dataBufferFactory.join(dataBuffers);
                            byte[] content = new byte[join.readableByteCount()];
                            join.read(content);
                            DataBufferUtils.release(join);
                            String responseData = new String(content, Charsets.UTF_8);
                            if (logger.isDebugEnabled()) {
                                logger.debug("响应转前:{}", responseData);
                            }
                            responseData = responseData.replaceAll(":null", ":\"\"");
                            if (logger.isDebugEnabled()) {
                                logger.debug("响应转后:{}", responseData);
                            }
                            byte[] uppedContent = responseData.getBytes(Charsets.UTF_8);

                            return bufferFactory.wrap(uppedContent);
                        }));
                    } else {
                        return chain.filter(exchange);
                    }
                }
            };
            return chain.filter(exchange.mutate().response(decoratedResponse).build());
        } catch (Exception e) {
            logger.error(" ReplaceNullFilter 异常", e);
            return chain.filter(exchange);
        }
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
