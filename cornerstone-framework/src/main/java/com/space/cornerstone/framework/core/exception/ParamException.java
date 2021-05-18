package com.space.cornerstone.framework.core.exception;

/**
 * 参数异常
 *
 * @author chen qi
 * @date 2021-05-18 14:51
 **/
public class ParamException extends BaseException {
    private static final long serialVersionUID = -6912618737345878854L;

    public ParamException(String message) {
        super(message);
    }

    public ParamException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public ParamException(BaseErrorInfoInterface errorInfoInterface) {
        super(errorInfoInterface);
    }
}