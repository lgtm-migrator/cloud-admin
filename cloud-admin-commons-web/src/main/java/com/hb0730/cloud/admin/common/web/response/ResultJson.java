package com.hb0730.cloud.admin.common.web.response;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 *     同一返回格式
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class ResultJson<T>  implements Serializable {


    private static final long serialVersionUID = -57L;

    /**
     * 错误码
     */
    private int errCode;
    /**
     * 错误信息
     */
    private String errorMessage;
    /**
     * 响应数据
     */
    private T data;

    public ResultJson() {
    }

    public ResultJson(int errCode, String errorMessage, T data) {
        this.errCode = errCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
        return errCode == that.errCode &&
                Objects.equals(errorMessage, that.errorMessage) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errCode, errorMessage, data);
    }

    @Override
    public String toString() {
        return "ResultJson{" +
                "errCode=" + errCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
