package com.space.cornerstone.framework.core.exception;

import cn.hutool.json.JSON;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName GlobalExceptionHandler.java
 * @Description 全局异常捕获类
 * @createTime 2021年05月30日 10:02:00
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 非法参数验证异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ReturnModel<List<String>> handleMethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> list = new ArrayList<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            list.add(fieldError.getDefaultMessage());
        }
        Collections.sort(list);
        return ReturnModel.error(CommonEnum.PARAM_CODE_EXCEPTION.getCode(), list);
    }


    /**
     * 业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ReturnModel<String> businessException(BusinessException e) {
        log.error("[EXCEPTION]={}: ", CommonEnum.BUSINESS_EXCEPTION, e);
        if (e.getErrorCode() == null) {
            return ReturnModel.error(e.getMessage());
        }
        return ReturnModel.error(e.getErrorCode(), e.getMessage());
    }


    /**
     * 默认的异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    public ReturnModel<String> httpRequestMethodNotSupportedExceptionHandler(Exception exception) {
        return ReturnModel.error(CommonEnum.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION);
    }


    /**
     * 异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ReturnModel<String> exception(Exception e) {

        CommonEnum commonEnum;
        if (e instanceof RedisException) {
            commonEnum = CommonEnum.REDIS_CODE_EXCEPTION;
        } else if (e instanceof UtilException) {
            commonEnum = CommonEnum.UTIL_CODE_EXCEPTION;
        } else if (e instanceof DaoException) {
            commonEnum = CommonEnum.DAO_EXCEPTION;
        } else if (e instanceof ParamException) {
            commonEnum = CommonEnum.PARAM_CODE_EXCEPTION;
        } else if (e instanceof CaptchaException) {
            commonEnum = CommonEnum.CAPTCHA_CODE_EXCEPTION;
        } else if (e instanceof CaptchaExpireException) {
            commonEnum = CommonEnum.CAPTCHA_EXPIRE_CODE_EXCEPTION;
        } else if (e instanceof NoHandlerFoundException) {
            commonEnum = CommonEnum.NOT_FOUND;
        } else if (e instanceof AccessDeniedException) {
            commonEnum = CommonEnum.NOT_PERMISSION;
        } else if (e instanceof UsernameNotFoundException) {
            commonEnum = CommonEnum.USERNAME_NOT_FOUND_CODE_EXCEPTION;
        } else if (e instanceof DataAccessException) {
            commonEnum = CommonEnum.DATA_ACCESS_CODE_EXCEPTION;
        } else {
            commonEnum = CommonEnum.SYSTEM_EXCEPTION;
        }

        log.error("[EXCEPTION]={}: ", commonEnum, e);
        return ReturnModel.error(commonEnum);
    }



}
