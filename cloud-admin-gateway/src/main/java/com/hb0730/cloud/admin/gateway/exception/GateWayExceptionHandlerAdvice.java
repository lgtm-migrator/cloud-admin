package com.hb0730.cloud.admin.gateway.exception;

import com.hb0730.cloud.admin.common.exception.AbstractCloudAdminException;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import io.netty.channel.ConnectTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class GateWayExceptionHandlerAdvice {
    private static Logger logger = LoggerFactory.getLogger(GateWayExceptionHandlerAdvice.class);

    /**
     * <p>
     * 非法请求
     * </p>
     *
     * @param ex ResponseStatusException
     * @return 非法请求
     */
    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResultJson handle(ResponseStatusException ex) {
        logger.error("response status exception:{}", ex.getMessage());
        return ResponseResult.result(CodeStatusEnum.ILLEGAL_REQUEST, ex.getMessage());
    }

    /**
     * 连接超时
     *
     * @param ex ConnectTimeoutException
     * @return 连接超时
     */
    @ExceptionHandler(value = {ConnectTimeoutException.class})
    public ResultJson handle(ConnectTimeoutException ex) {
        logger.error("connect timeout exception:{}", ex.getMessage());
        return ResponseResult.result(CodeStatusEnum.CONNECT_TIME_OUT, ex.getMessage());
    }

    /**
     * 业务异常
     *
     * @param e 系统业务异常
     * @return 业务异常
     */
    @ExceptionHandler(value = {AbstractCloudAdminException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultJson handler(AbstractCloudAdminException e) {
        logger.error("Admin exception ：{}", e.getMessage());
        return new ResultJson<>(e.getStatus(), e.getMessage(), e.getErrorData());
    }

    /**
     * <p>
     * 异常
     * </p>
     *
     * @param ex Exception
     * @return 异常
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultJson handle(Exception ex) {
        logger.error("exception:{}", ex.getMessage());
        return ResponseResult.resultFall(ex.getMessage());
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultJson handle(Throwable throwable) {
        ResultJson result = ResponseResult.result(CodeStatusEnum.ILLEGAL_REQUEST, null);
        if (throwable instanceof ResponseStatusException) {
            result = handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            result = handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof AbstractCloudAdminException) {
            result = handle((AbstractCloudAdminException) throwable);
        } else if (throwable instanceof RuntimeException) {
            result = handle((RuntimeException) throwable);
        } else if (throwable instanceof Exception) {
            result = handle((Exception) throwable);
        }
        return result;
    }
}
