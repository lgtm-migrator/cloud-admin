package com.hb0730.cloud.admin.server.post.system.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.hb0730.cloud.admin.commons.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统岗位
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_post")
public class SystemPostEntity extends BaseDomain {

    private static final long serialVersionUID=1L;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    @TableLogic
    private Integer isDelete;

    /**
     * 是否启用
     */
    @TableField(value = "is_enabled", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Integer isEnabled;

    /**
     * id
     */
    @TableField(value = "id", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Long id;

    /**
     * 岗位编码
     */
    @TableField(value = "code", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String code;

    /**
     * 岗位名称
     */
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String name;

    /**
     * 排序
     */
    @TableField(value = "sort", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Integer sort;

    /**
     * 描述
     */
    @TableField(value = "`describe`", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String describe;

    public static final String IS_DELETE = "is_delete";

    public static final String IS_ENABLED = "is_enabled";

    public static final String ID = "id";

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String SORT = "sort";

    public static final String DESCRIBE="describe";

}
