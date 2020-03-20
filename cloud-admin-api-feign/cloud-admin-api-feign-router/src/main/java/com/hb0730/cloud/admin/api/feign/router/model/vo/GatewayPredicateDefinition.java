package com.hb0730.cloud.admin.api.feign.router.model.vo;

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 路由断言
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GatewayPredicateDefinition {
    /**
     * 断言对应的Name
     */
    private String name;
    /**
     * 配置的断言规则
     */
    private Map<String, String> args = new LinkedHashMap<>();
}
