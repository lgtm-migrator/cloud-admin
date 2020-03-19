package com.hb0730.cloud.admin.common.web.utils;

/**
 * <p>
 * 响应状态
 * </p>
 *
 * @author bing_huang
 * @date 2020/02/13
 * @since v1.1
 */
public enum CodeStatusEnum {
    /**
     * 请求成功
     */
    SUCCESS("CA20000", "请求成功"),
    /**
     * 请求失败
     */
    FAIL("CA20002", "请求失败"),
    /**
     * 非法请求
     */
    ILLEGAL_REQUEST("CA10001", "非法请求"),
    /**
     * 连接超时
     */
    CONNECT_TIME_OUT("CA10002", "连接超时"),
    /**
     * 无权限
     */
    AUTH_ERROR("CA10003", "抱歉，您没有操作权限"),
    /**
     * 非法令牌
     */
    ILLEGAL_TOKEN("CA10004", "非法令牌"),
    /**
     * 其他客户端登录
     */
    OTHER_CLIENTS_LOGGED_IN("CA10005", "其他客户登录"),
    /**
     * 令牌已过期
     */
    TOKEN_EXPIRED("CA10006", "令牌已过期"),
    /**
     * 登录失败
     */
    LOGIN_FAILURE("CA10007", "登录失败"),
    /**
     * 用户未登录
     */
    NON_LOGIN("CA10008", "用户未登录"),
    /**
     * 未找到
     */
    NOT_FONT("CA10009", "未找到"),
    /**
     * 限流
     */
    CURRENT_LIMITING("CA10010", "限流"),
    /**
     * 熔断
     */
    FALL_BACK("CA10011", "熔断"),

    BEAN_EXCEPTION("CA10012", "bean异常"),

    BUSINESS_EXCEPTION("CA10012", "业务异常"),

    GATEWAY_EXCEPTION("CA10013", "网关异常"),

    NULL_EXCEPTION("CA10014", "空指针异常"),

    OAUTH2_EXCEPTION("CA10015", "认证异常"),
    UNKNOWN_EXCEPTION("CA99999", "未知");
    private String status;
    private String message;

    CodeStatusEnum(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getCode() {
        return status;
    }

    public void setCode(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
