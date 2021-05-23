package com.space.cornerstone.framework.core.domain.model;

import com.space.cornerstone.framework.core.exception.BaseErrorInfoInterface;

import java.io.Serializable;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName ReturnModel.java
 * @Description 返回类
 * @createTime 2021年05月20日 08:34:00
 */
public class ReturnModel<T> implements Serializable {

    private static final long serialVersionUID = -6503145232078692951L;

    /**
     * 响应是否成功
     */
    private boolean success;

    /**
     * 响应状态码
     */
    private int status;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 信息
     */
    private String message;

    public ReturnModel() {
        this.status = -1;
    }

    public ReturnModel(int status, T data) {
        this(status == 200, status, data);
    }
    public ReturnModel(int status, String message) {
        this(status == 200, status, null, message);
    }

    public ReturnModel(T data) {
        this(true, data);
    }

    public ReturnModel(boolean success) {
        this(success, null);
    }

    public ReturnModel(boolean success, T data) {
        this(success, success ? 200 : -1, data);
    }

    public ReturnModel(boolean success, int status, T data) {
        this.success = success;
        this.status = status;
        if (success) {
            this.data = data;
        } else if (data != null) {
            this.message = data.toString();
        }
    }

    public ReturnModel(boolean success, int status, T data, String message) {
        this.success = success;
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ReturnModel<T> success(boolean success) {
        this.success = success;
        return this;
    }

    public static <T> ReturnModel<T> success() {
        return new ReturnModel<>(true);
    }

    public static <T> ReturnModel<T> success(T t) {
        return new ReturnModel<>(t);
    }

    public static <T> ReturnModel<T> ok() {
        return new ReturnModel<>(true);
    }

    public static <T> ReturnModel<T> ok(T t) {
        return new ReturnModel<>(t);
    }

    public static <T> ReturnModel<T> message(int status, String t) {
        return new ReturnModel<>(status, t);
    }

    public static <T> ReturnModel<T> error(T t) {
        return new ReturnModel<>(false, t);
    }

    public static <T> ReturnModel<T> error(int status, T t) {
        return new ReturnModel<>(status, t);
    }

    public static ReturnModel<String> error(BaseErrorInfoInterface errorInfo) {
        return new ReturnModel<>(errorInfo.getCode(), errorInfo.getMessage());
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ReturnModel<T> message(T message) {
        this.data = message;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReturnModel<T> errorMessage(String errorMessage) {
        this.message = errorMessage;
        return this;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ReturnModel<T> status(int status) {
        this.status = status;
        return this;
    }
}
