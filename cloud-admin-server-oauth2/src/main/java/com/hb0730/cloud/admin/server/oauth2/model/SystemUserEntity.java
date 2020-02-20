package com.hb0730.cloud.admin.server.oauth2.model;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.hb0730.cloud.admin.commons.domain.BaseDomain;
import lombok.Data;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-15
 */
@Data
public class SystemUserEntity extends BaseDomain {

    private static final long serialVersionUID = 1L;
    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete = 0;

    /**
     * 是否启用
     */
    private Integer isEnabled = 1;

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 注册邮箱
     */
    private String email;

    /**
     * 头像
     */
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
