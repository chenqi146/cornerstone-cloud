package com.space.cornerstone.framework.core.exception;

/**
 * 验证码过期异常
 *
 * @author chen qi
 * @date 2021-05-18 14:50
 **/
public class CaptchaExpireException extends BaseException {
    private static final long serialVersionUID = -2303357122330162359L;

    public CaptchaExpireException() {
        super(CommonEnum.CAPTCHA_EXPIRE_CODE_EXCEPTION);
    }
    public CaptchaExpireException(String message) {
        super(message);
    }

    public CaptchaExpireException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public CaptchaExpireException(BaseErrorInfoInterface errorInfoInterface) {
        super(errorInfoInterface);
    }

    public CaptchaExpireException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
