package com.space.cornerstone.framework.core.xss;


import cn.hutool.core.util.EscapeUtil;
import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * XSS 跨站脚本攻击(Cross Site Scripting) 处理
 *
 * @author chen qi
 **/
public class XssAndSqlHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final HttpServletRequest request;
    public XssAndSqlHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getQueryString() {
        return EscapeUtil.escapeHtml4(super.getQueryString());
    }

    @Override
    public String getParameter(String name) {
        String value = request.getParameter(name);
        if (StrUtil.isNotEmpty(value)) {
            value = EscapeUtil.escapeHtml4(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameterValues = super.getParameterValues(name);
        if (parameterValues == null) {
            return null;
        }
        for (int i = 0; i < parameterValues.length; i++) {
            String value = parameterValues[i];
            parameterValues[i] = EscapeUtil.escapeHtml4(value);
        }
        return parameterValues;
    }

}
