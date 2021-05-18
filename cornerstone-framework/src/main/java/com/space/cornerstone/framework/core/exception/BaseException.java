package com.space.cornerstone.framework.core.exception;

import com.google.common.base.Strings;
import lombok.Data;

/**
 * @ClassName: BaseException
 * @Description: 基础异常类
 * @Author: chen qi
 * @Date: 2021/5/18 12:14
 * @Version: 1.0
 **/
@Data
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -2470461654663233321L;

    private Integer errorCode;
    private String message;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(Integer errorCode, String template, Object... messages) {
        super(Strings.lenientFormat(template, messages));
        this.errorCode = errorCode;
        this.message = Strings.lenientFormat(template, messages);
    }

    public BaseException(BaseErrorInfoInterface errorInfoInterface) {
        super(errorInfoInterface.getMessage());
        this.errorCode = errorInfoInterface.getCode();
        this.message = errorInfoInterface.getMessage();
    }
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

}
