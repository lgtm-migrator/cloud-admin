package com.hb0730.cloud.admin.common.web.exception;

import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
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
    private String errorMessage;

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
    public abstract CodeStatusEnum getStatus();

    @Nullable
    public String getErrorData() {
        return errorMessage;
    }

    /**
     * Sets error errorData.
     *
     * @param errorMessage error data
     * @return current exception.
     */
    @NonNull
    public AbstractCloudAdminException setErrorData(@Nullable String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
