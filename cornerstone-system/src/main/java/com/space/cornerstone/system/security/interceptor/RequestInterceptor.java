package com.space.cornerstone.system.security.interceptor;

import cn.hutool.core.util.StrUtil;
import com.space.cornerstone.framework.core.auth.Auth;
import com.space.cornerstone.framework.core.domain.model.AuthUser;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import com.space.cornerstone.framework.core.exception.CommonEnum;
import com.space.cornerstone.framework.core.util.JacksonUtil;
import com.space.cornerstone.system.service.TokenService;
import com.space.cornerstone.system.util.ServletUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName RequestInterceptor.java
 * @Description RequestInterceptor
 * @createTime 2021年05月23日 10:29:00
 */
@Component
@RequiredArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 放行OPTIONS
        String method = request.getMethod();
        if (Objects.equals(method, HttpMethod.OPTIONS.name())) {
            return false;
        }

        String token = ServletUtil.getToken(request);
        if (StrUtil.isEmpty(token)) {
            String msg = StrUtil.format("此请求({})没有权限", request.getRequestURI());
            ServletUtil.returnString(response, JacksonUtil.toJson(ReturnModel.message(CommonEnum.NOT_PERMISSION.getCode(), msg)));
            return false;
        }

        AuthUser user = tokenService.getUser(token);
        Auth.setUser(user);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Auth.clear();
    }
}
