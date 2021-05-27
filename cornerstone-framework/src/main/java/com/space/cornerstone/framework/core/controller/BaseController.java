package com.space.cornerstone.framework.core.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName BaseController.java
 * @Description BaseController
 * @createTime 2021年05月18日 23:39:00
 */
public class BaseController {

    protected static final Logger log = LoggerFactory.getLogger(BaseController.class);

    public BaseController() {
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Date date = null;
                try {
                    if (StrUtil.isNotEmpty(text)) {
                        date = DateUtil.parse(text, DatePattern.NORM_DATETIME_PATTERN);
                    }
                } catch (Exception e) {
                    log.error("controller中Date转换异常, error: ", e);
                }
                setValue(date);
            }

        });


        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text){
                LocalDateTime dateValue = null;
                Exception exception = null;
                try {
                    if (StrUtil.isEmpty(text)) {
                        return;
                    }
                    dateValue = LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                } catch (Exception e) {
                    exception = e;
                }

                try {
                    if (StrUtil.isEmpty(text)) {
                        return;
                    }
                    dateValue = LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE).atTime(LocalTime.MIN);
                } catch (Exception e) {
                    exception = e;
                }

                if (dateValue == null) {
                    log.error("controller中LocalDateTime日期转换异常：{}, error: ", text, exception);
                }
                setValue(dateValue);
            }
        });

        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text){
                LocalDate dateValue = null;
                try {
                    if (StrUtil.isEmpty(text)) {
                        return;
                    }
                    dateValue = LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE);
                } catch (Exception e) {
                    log.error("controller中LocalDateTime日期转换异常：{}, error: ", text, e);
                }
                setValue(dateValue);
            }
        });
    }
}
