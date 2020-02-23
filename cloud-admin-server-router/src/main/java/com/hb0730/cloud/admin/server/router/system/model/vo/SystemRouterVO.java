package com.hb0730.cloud.admin.server.router.system.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hb0730.cloud.admin.common.web.vo.BusinessDomainVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class SystemRouterVO extends BusinessDomainVO {
    /**
     * 是否删除
     */
    private Integer isDelete = 0;

    /**
     * 是否启用
     */
    private Integer isEnabled = 1;

    /**
     * id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 备注
     */
    private String description;

    /**
     * 路由过滤器(JSON)
     */
    private String filters;

    /**
     * 断言(json)
     */
    private String predicates;

    /**
     * 转发的目标uri
     */
    private String uri;

    /**
     * 执行顺序
     */
    private Integer order;
}
