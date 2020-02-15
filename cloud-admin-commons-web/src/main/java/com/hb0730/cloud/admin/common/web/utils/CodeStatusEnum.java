package com.hb0730.cloud.admin.common.web.utils;

/**
 * <p>
 * 响应状态
 * </p>
 *
 * @author bing_huang
 * @date 2020/02/13
 */
public enum CodeStatusEnum {
    /**
     * 请求成功
     */
    SUCCESS(200,"请求成功"),
    /**
     * 请求失败
     */
    FAIL(202,"请求失败"),
    /**
     * 非法请求
     */
    ILLEGAL_REQUEST(500,"非法请求"),
    /**
     * 连接超时
     */
    CONNECT_TIME_OUT(500,"连接超时"),
    /**
     * 无权限
     */
    AUTH_ERROR(5401, "抱歉，您没有操作权限"),
    /**
     * 非法令牌
     */
    ILLEGAL_TOKEN(508,"非法令牌"),
    /**
     * 其他客户端登录
     */
    OTHER_CLIENTS_LOGGED_IN(512,"其他客户登录"),
    /**
     * 令牌已过期
     */
    TOKEN_EXPIRED(514,"令牌已过期"),
    /**
     * 登录失败
     */
    LOGIN_FAILURE(514,"登录失败"),
    /**
     * 用户未登录
     */
    NON_LOGIN(514,"用户未登录"),
    /**
     * 未找到
     */
    NOT_FONT(404,"未找到");

    private Integer code;
    private String message;

    CodeStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
