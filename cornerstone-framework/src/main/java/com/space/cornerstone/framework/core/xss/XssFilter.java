
package com.space.cornerstone.framework.core.xss;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Xss过滤器
 *
 * @author chen qi
 **/
@Slf4j
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        XssAndSqlHttpServletRequestWrapper xssRequestWrapper = new XssAndSqlHttpServletRequestWrapper(req);
        chain.doFilter(xssRequestWrapper, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("XssFilter destroy");
    }
}
