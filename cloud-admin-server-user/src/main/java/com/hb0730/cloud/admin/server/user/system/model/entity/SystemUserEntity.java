package com.hb0730.cloud.admin.server.user.system.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.hb0730.cloud.admin.commons.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_user")
public class SystemUserEntity extends BaseDomain {

    private static final long serialVersionUID=1L;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    @TableLogic
    private Integer isDelete=0;

    /**
     * 是否启用
     */
    @TableField(value = "is_enabled", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Integer isEnabled=1;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String name;

    /**
     * 登录账号
     */
    @TableField(value = "username", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String password;

    /**
     * 手机号
     */
    @TableField(value = "phone", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String phone;

    /**
     * 注册邮箱
     */
    @TableField(value = "email", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String email;

    /**
     * 头像
     */
    @TableField(value = "portraits", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String portraits;


    public static final String IS_DELETE = "is_delete";

    public static final String IS_ENABLED = "is_enabled";

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String PHONE = "phone";

    public static final String EMAIL = "email";

    public static final String PORTRAITS = "portraits";

}
