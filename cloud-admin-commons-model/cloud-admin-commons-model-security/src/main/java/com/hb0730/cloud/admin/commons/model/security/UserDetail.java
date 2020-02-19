package com.hb0730.cloud.admin.commons.model.security;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class UserDetail implements Serializable {
    private static final long serialVersionUID = -18396L;
    private Integer userId;
    private String username;
    private String password;
    private String salt;
    private Integer status;
    private String perms;
    private String avatar;
    private String sex;
    private Integer deptId;
    private String deptName;
    private String email;
    private String phone;
    private String describe;
    private String roleName;
}
