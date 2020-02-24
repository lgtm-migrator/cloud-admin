package com.hb0730.cloud.admin.server.user.system.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class SettingPasswordParams implements Serializable {
    private String oldPassword;
    private String newPassword;
    private String newPassword2;
}
