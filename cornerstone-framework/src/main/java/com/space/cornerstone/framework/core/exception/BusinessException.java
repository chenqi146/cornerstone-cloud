package com.space.cornerstone.framework.core.exception;

/**
 * 业务异常
 *
 * @author chen qi
 * @date 2021-05-18 14:50
 **/
public class BusinessException extends BaseException {
    private static final long serialVersionUID = -2303357122330162359L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public BusinessException(BaseErrorInfoInterface errorInfoInterface) {
        super(errorInfoInterface);
    }

}
