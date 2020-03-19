package com.hb0730.cloud.admin.common.web.response;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 同一返回格式
 * </P>
 *
 * @author bing_huang
 * @since V1.1
 */
public class ResultJson<T> implements Serializable {


    private static final long serialVersionUID = -57L;

    /**
     * 错误码
     */
    private String statusCode;
    /**
     * 错误信息
     */
    private String statusMessage;
    /**
     * 响应数据
     */
    private T data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultJson<?> that = (ResultJson<?>) o;
        return Objects.equals(statusCode, that.statusCode) &&
                Objects.equals(statusMessage, that.statusMessage) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, statusMessage, data);
    }

    @Override
    public String toString() {
        return "ResultJson{" +
                "statusCode='" + statusCode + '\'' +
                ", statusMessage='" + statusMessage + '\'' +
                ", data=" + data +
                '}';
    }

    public ResultJson() {
    }

    public ResultJson(String statusCode, String statusMessage, T data) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.data = data;
    }
}
