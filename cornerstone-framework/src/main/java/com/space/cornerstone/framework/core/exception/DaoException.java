package com.space.cornerstone.framework.core.exception;

/**
 * 数据库异常
 *
 * @author chen qi
 * @date 2021-05-18 14:51
 **/
public class DaoException extends BaseException {
    private static final long serialVersionUID = -6912618737345878854L;

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public DaoException(BaseErrorInfoInterface errorInfoInterface) {
        super(errorInfoInterface);
    }
}