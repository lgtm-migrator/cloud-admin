package com.hb0730.cloud.admin.server.router.system.model.vo;

import com.hb0730.cloud.admin.common.web.vo.BusinessDomainVO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 路由定义
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GatewayRouteDefinition extends BusinessDomainVO {

    private static final long serialVersionUID = -13L;
    /**
     * 路由的Id
     */
    private String id;
    /**
     * <p>
     * 描述
     * </p>
     */
    private String description;
    /**
     * 路由断言集合配置
     */
    private List<GatewayPredicateDefinition> predicates = new ArrayList<>();
    /**
     * 路由过滤器集合配置
     */
    private List<GatewayFilterDefinition> filters = new ArrayList<>();
    /**
     * 路由规则转发的目标uri
     */
    private String uri;
    /**
     * 路由执行的顺序
     */
    private int order = 0;
}
