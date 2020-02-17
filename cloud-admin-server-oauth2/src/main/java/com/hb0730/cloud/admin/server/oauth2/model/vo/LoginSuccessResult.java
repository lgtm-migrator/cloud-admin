package com.hb0730.cloud.admin.server.oauth2.model.vo;

import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 用户登录成功后返回参数
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
public class LoginSuccessResult implements Serializable {

    private static final long serialVersionUID = -386495254260L;

    /**
     * <p>
     * access_token
     * </p>
     */
    private String accessToken;

    /**
     * <p>
     * refresh_token
     * </p>
     */
    private String refreshToken;

    /**
     * <p>
     * 用户名
     * </p>
     */
    private String userName;
}
