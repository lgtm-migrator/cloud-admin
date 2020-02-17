package com.hb0730.cloud.admin.server.oauth2.model.dto;

import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 登录参数
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginParams implements Serializable {

    private static final long serialVersionUID = -112L;

    private String userName;
    private String password;
}
