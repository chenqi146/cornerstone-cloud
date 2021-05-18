package com.space.cornerstone.common.exception;

import com.google.common.base.Strings;

/**
 * @ClassName: BaseException
 * @Description: 基础异常类
 * @Author: chen qi
 * @Date: 2021/5/18 12:14
 * @Version: 1.0
 **/
public abstract class BaseException extends RuntimeException {


    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String template, Object... messages) {
        super(Strings.lenientFormat(template, messages));
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ";";
    }

    public abstract Integer getErrorCode();
    public abstract String getErrorMsg();
}
