package com.hb0730.cloud.admin.common.web.exception;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * <p>
 * 异常捕获
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@ControllerAdvice
public class CloudAdminExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(CloudAdminExceptionHandler.class);

    @ExceptionHandler(BeanUtilsException.class)
    public ResultJson errorHandler(BeanUtilsException exception) {
        String message = String.format("Class:【%s】,message:{%s>>>>>%s}", exception.getClass().getSimpleName(), exception.getMessage(), exception.getErrorData());
        logger.error(message);
        return ResponseResult.result(CodeStatusEnum.BEAN_EXCEPTION, message);
    }

    @ExceptionHandler(BusinessException.class)
    public ResultJson errorHandler(BusinessException exception) {
        String message = String.format("Class:【%s】,message:{%s>>>>>%s}", exception.getClass().getSimpleName(), exception.getMessage(), exception.getErrorData());
        logger.error(message);
        return ResponseResult.result(CodeStatusEnum.BUSINESS_EXCEPTION, message);
    }

    @ExceptionHandler(GatewayException.class)
    public ResultJson errorHandler(GatewayException exception) {
        String message = String.format("Class:【%s】,message:{%s>>>>>%s}", exception.getClass().getSimpleName(), exception.getMessage(), exception.getErrorData());
        logger.error(message);
        return ResponseResult.result(CodeStatusEnum.GATEWAY_EXCEPTION, message);
    }

    @ExceptionHandler(NotFondException.class)
    public ResultJson errorHandler(NotFondException exception) {
        String message = String.format("Class:【%s】,message:{%s>>>>>%s}", exception.getClass().getSimpleName(), exception.getMessage(), exception.getErrorData());
        logger.error(message);
        return ResponseResult.result(CodeStatusEnum.NOT_FONT, message);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResultJson errorHandler(NullPointerException exception) {
        String message = String.format("Class:【%s】,message:{%s>>>>>%s}", exception.getClass().getSimpleName(), exception.getMessage(), exception.getErrorData());
        logger.error(message);
        return ResponseResult.result(CodeStatusEnum.NULL_EXCEPTION, message);
    }

    @ExceptionHandler(Oauth2Exception.class)
    public ResultJson errorHandler(Oauth2Exception exception) {
        String message = String.format("Class:【%s】,message:{%s>>>>>%s}", exception.getClass().getSimpleName(), exception.getMessage(), exception.getErrorData());
        logger.error(message);
        return ResponseResult.result(CodeStatusEnum.OAUTH2_EXCEPTION, message);
    }

    @ExceptionHandler(UserNonLoginException.class)
    public ResultJson errorHandler(UserNonLoginException exception) {
        String message = String.format("Class:【%s】,message:{%s>>>>>%s}", exception.getClass().getSimpleName(), exception.getMessage(), exception.getErrorData());
        logger.error(message);
        return ResponseResult.result(CodeStatusEnum.NON_LOGIN, message);
    }

    @ExceptionHandler(Exception.class)
    public ResultJson errorHandler(Exception exception) {
        String message = String.format("Class:【%s】,message:{%s>>>>>%s}", exception.getClass().getSimpleName(), exception.getMessage(), "");
        logger.error(message);
        return ResponseResult.result(CodeStatusEnum.FAIL, message);
    }

}
