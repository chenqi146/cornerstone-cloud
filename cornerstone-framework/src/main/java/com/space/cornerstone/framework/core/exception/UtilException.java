package com.space.cornerstone.framework.core.exception;

/**
 * UtilException
 *
 * @author chen qi
 * @date 2021-05-18 14:51
 **/
public class UtilException extends BaseException {
    private static final long serialVersionUID = -6912618737345878854L;

    public UtilException(String message) {
        super(message);
    }

    public UtilException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public UtilException(BaseErrorInfoInterface errorInfoInterface) {
        super(errorInfoInterface);
    }


    public UtilException(Throwable cause) {
        super(cause);
    }

    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }
}