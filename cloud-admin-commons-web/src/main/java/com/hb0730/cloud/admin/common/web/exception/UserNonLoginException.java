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
public class UserNonLoginException extends AbstractCloudAdminException {
    public UserNonLoginException(String message) {
        super(message);
    }

    public UserNonLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public @NonNull CodeStatusEnum getStatus() {
        return CodeStatusEnum.NON_LOGIN;
    }
}
