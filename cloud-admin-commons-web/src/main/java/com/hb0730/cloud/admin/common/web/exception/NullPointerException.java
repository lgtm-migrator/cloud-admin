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
public class NullPointerException extends AbstractCloudAdminException {
    public NullPointerException(String message) {
        super(message);
    }

    public NullPointerException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public @NonNull CodeStatusEnum getStatus() {
        return CodeStatusEnum.NULL_EXCEPTION;
    }
}
