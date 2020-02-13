package com.hb0730.cloud.admin.commons.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 领域模型的基类
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
@ToString
public class BaseDomain implements Serializable {
    private static final long serialVersionUID = -39L;

    /**
     * 创建人
     */
    @TableField(value = "create_user_id", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Integer createUserId;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Date createTime;
    /**
     * 修改人
     */
    @TableField(value = "update_user_id", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Integer updateUserId;
    /**
     * 修改时间
     */
    @TableField(value = "update_time", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Date updateTime;
    /**
     * 版本
     */
    @TableField(value = "version", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    @Version
    private Integer version;

    public static final String CREATE_USER = "create_user";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_USER = "update_user";

    public static final String UPDATE_TIME = "update_time";

    public static final String VERSION = "version";
}
