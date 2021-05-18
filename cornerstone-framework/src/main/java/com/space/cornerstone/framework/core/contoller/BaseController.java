package com.space.cornerstone.framework.core.contoller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName BaseController.java
 * @Description BaseController
 * @createTime 2021年05月18日 23:39:00
 */
@Slf4j
public class BaseController {


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
        // TODO: 2021-05-18 localDate  and localDateTime convert
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Date date = null;
                try {
                    if (StrUtil.isNotEmpty(text)) {
                        date = DateUtil.parse(text, DatePattern.NORM_DATETIME_PATTERN);
                    }
                } catch (Exception e) {
                    log.error("controller中date转换异常, error: ", e);
                }
                setValue(date);
            }

        });
    }
}
