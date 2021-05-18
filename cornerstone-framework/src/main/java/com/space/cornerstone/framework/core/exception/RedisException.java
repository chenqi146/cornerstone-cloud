package com.space.cornerstone.framework.core.exception;

/**
 * redis异常
 *
 * @author chen qi
 * @date 2021-05-18 14:51
 **/
public class RedisException extends BaseException {
    private static final long serialVersionUID = -6912618737345878854L;

    public RedisException(String message) {
        super(message);
    }

    public RedisException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public RedisException(BaseErrorInfoInterface errorInfoInterface) {
        super(errorInfoInterface);
    }


    public RedisException(Throwable cause) {
        super(cause);
    }

}