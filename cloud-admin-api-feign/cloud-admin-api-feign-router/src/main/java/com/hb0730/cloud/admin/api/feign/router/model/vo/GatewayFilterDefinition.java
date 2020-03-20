package com.hb0730.cloud.admin.api.feign.router.model.vo;

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 过滤器
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class GatewayFilterDefinition {
    /**
     * Filter Name
     */
    private String name;
    /**
     * 对应的路由规则
     */
    private Map<String, String> args = new LinkedHashMap<>();
}
