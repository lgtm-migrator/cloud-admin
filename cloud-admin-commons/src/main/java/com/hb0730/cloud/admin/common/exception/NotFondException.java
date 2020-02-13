package com.hb0730.cloud.admin.common.exception;

import lombok.NonNull;

/**
 * <p>
 * 未找到
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class NotFondException extends AbstractCloudAdminException {
    public NotFondException(String message) {
        super(message);
    }

    public NotFondException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public @NonNull int getStatus() {
        return 404;
    }
}
