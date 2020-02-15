package com.hb0730.cloud.admin.common.exception;

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
    public @NonNull int getStatus() {
        return 500;
    }
}
