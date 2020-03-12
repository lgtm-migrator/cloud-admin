package com.hb0730.cloud.admin.common.exception;

import lombok.NonNull;
import org.springframework.lang.Nullable;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public abstract class AbstractCloudAdminException extends RuntimeException {
    private Object errorData;

    public AbstractCloudAdminException(String message) {
        super(message);
    }

    public AbstractCloudAdminException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 错误状态
     *
     * @return 状态码
     */
    @NonNull
    public abstract int getStatus();

    @Nullable
    public Object getErrorData() {
        return errorData;
    }

    /**
     * Sets error errorData.
     *
     * @param errorData error data
     * @return current exception.
     */
    @NonNull
    public AbstractCloudAdminException setErrorData(@Nullable Object errorData) {
        this.errorData = errorData;
        return this;
    }
}
