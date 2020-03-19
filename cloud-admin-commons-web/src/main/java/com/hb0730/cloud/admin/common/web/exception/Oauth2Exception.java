package com.hb0730.cloud.admin.common.web.exception;

import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import lombok.NonNull;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class Oauth2Exception extends AbstractCloudAdminException {
    public Oauth2Exception(String message) {
        super(message);
    }

    public Oauth2Exception(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public @NonNull CodeStatusEnum getStatus() {
        return CodeStatusEnum.OAUTH2_EXCEPTION;
    }
}
