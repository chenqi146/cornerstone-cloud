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

    private boolean success;
    private int status;
    private T message;
    private String errorMessage;

    public ReturnModel() {
        this.status = -1;
    }

    public ReturnModel(int status, T message) {
        this(status == 200, status, message);
    }

    public ReturnModel(T message) {
        this(true, message);
    }

    public ReturnModel(boolean success) {
        this(success, null);
    }

    public ReturnModel(boolean success, T message) {
        this(success, success ? 200 : -1, message);
    }

    public ReturnModel(boolean success, int status, T message) {
        this.success = success;
        this.status = status;
        if (success) {
            this.message = message;
        } else if (message != null) {
            this.errorMessage = message.toString();
        }

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

    public static <T> ReturnModel<T> message(int status, T t) {
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

    public T getMessage() {
        return this.message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public ReturnModel<T> message(T message) {
        this.message = message;
        return this;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ReturnModel<T> errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
