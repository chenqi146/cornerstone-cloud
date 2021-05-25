package com.space.cornerstone.system.security;

import cn.hutool.core.util.StrUtil;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import com.space.cornerstone.framework.core.exception.CommonEnum;
import com.space.cornerstone.framework.core.util.JacksonUtil;
import com.space.cornerstone.framework.core.util.ServletUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 *
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = 5800222860003801693L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {

        String msg = StrUtil.format("此请求({})没有授权, 无法访问", request.getRequestURI());
        ServletUtil.returnString(response, JacksonUtil.toJson(ReturnModel.message(CommonEnum.UNAUTHORIZED.getCode(), msg)));
    }
}
