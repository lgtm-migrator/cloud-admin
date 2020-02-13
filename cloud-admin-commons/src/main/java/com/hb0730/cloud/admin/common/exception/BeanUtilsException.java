package com.hb0730.cloud.admin.common.exception;

import lombok.NonNull;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class BeanUtilsException extends AbstractCloudAdminException {
    public BeanUtilsException(String message) {
        super(message);
    }

    public BeanUtilsException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public @NonNull int getStatus() {
        return 500;
    }
}
