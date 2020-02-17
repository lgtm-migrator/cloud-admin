package com.hb0730.cloud.admin.server.oauth2.model;

import com.hb0730.cloud.admin.commons.config.domain.BaseDomain;
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
     * 用户账户
     */
    private String login;

    /**
     * 用户名
     */
    private String loginName;

    /**
     * 用户密码
     */
    private String loginPasswd;

    /**
     * email
     */
    private String loginEmail;

    /**
     * 盐值
     */
    private String salt;


    public static final String IS_DELETE = "is_delete";

    public static final String IS_ENABLED = "is_enabled";

    public static final String ID = "id";

    public static final String LOGIN = "login";

    public static final String LOGIN_NAME = "login_name";

    public static final String LOGIN_PASSWD = "login_passwd";

    public static final String LOGIN_EMAIL = "login_email";

    public static final String SALT = "salt";

}
