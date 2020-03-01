package com.hb0730.cloud.admin.common.exception;

import lombok.NonNull;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class UserNonLoginException extends AbstractCloudAdminException {
    public UserNonLoginException(String message) {
        super(message);
    }

    public UserNonLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public @NonNull int getStatus() {
        return 514;
    }
}
