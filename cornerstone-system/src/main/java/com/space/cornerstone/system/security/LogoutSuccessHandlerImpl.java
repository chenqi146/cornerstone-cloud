package com.space.cornerstone.system.security;

import com.space.cornerstone.framework.core.domain.model.AuthUser;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import com.space.cornerstone.framework.core.util.JacksonUtil;
import com.space.cornerstone.system.service.TokenService;
import com.space.cornerstone.system.util.ServletUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 *
 */
@Component
@RequiredArgsConstructor
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private final TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        // TODO: 2021-05-23 此处应该从现场变量中获取
        AuthUser loginUser = tokenService.getUser(ServletUtil.getToken(request));
        if (loginUser != null) {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.clearUser(loginUser.getToken());
            // TODO 记录用户退出日志
        }

        ServletUtil.returnString(response, JacksonUtil.toJson(ReturnModel.message(HttpStatus.OK.value(), "退出成功")));
    }
}
