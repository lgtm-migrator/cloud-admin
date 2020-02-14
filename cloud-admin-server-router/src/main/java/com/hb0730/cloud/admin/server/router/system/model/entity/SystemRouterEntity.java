package com.hb0730.cloud.admin.server.router.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.hb0730.cloud.admin.commons.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 路由
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_router")
public class SystemRouterEntity extends BaseDomain {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    /**
     * 是否删除
     */
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete=0;

    /**
     * 是否启用
     */
    @TableField("is_enabled")
    private Integer isEnabled=1;

    /**
     * 路由断言(JSON)
     */
    @TableField("predicates")
    private String predicates;

    /**
     * 路由过滤器(JSON)
     */
    @TableField("filters")
    private String filters;

    /**
     * 路由规则转发的目标uri
     */
    @TableField("uri")
    private String uri;

    /**
     * 执行顺序
     */
    @TableField("`order`")
    private Integer order;


    public static final String ID = "id";

    public static final String UPDATE_USER_ID = "update_user_id";

    public static final String IS_DELETE = "is_delete";

    public static final String IS_ENABLED = "is_enabled";

    public static final String PREDICATES = "predicates";

    public static final String FILTERS = "filters";

    public static final String URI = "uri";

    public static final String ORDER = "order";

}
