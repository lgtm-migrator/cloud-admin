package com.hb0730.cloud.admin.server.post.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.hb0730.cloud.admin.commons.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableField;
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
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;

    /**
     * 是否启用
     */
    @TableField("is_enabled")
    private Integer isEnabled;

    /**
     * id
     */
    @TableField("id")
    private Long id;

    /**
     * 岗位编码
     */
    @TableField("code")
    private String code;

    /**
     * 岗位名称
     */
    @TableField("name")
    private String name;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;


    public static final String IS_DELETE = "is_delete";

    public static final String IS_ENABLED = "is_enabled";

    public static final String ID = "id";

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String SORT = "sort";

}
