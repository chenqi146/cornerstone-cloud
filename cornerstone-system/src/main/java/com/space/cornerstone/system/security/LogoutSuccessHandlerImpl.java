package com.space.cornerstone.system.security;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.domain.entity.system.SysLoginLog;
import com.space.cornerstone.framework.core.domain.model.AuthUser;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import com.space.cornerstone.framework.core.exception.CommonEnum;
import com.space.cornerstone.framework.core.service.SysLoginLogService;
import com.space.cornerstone.framework.core.util.IpUtil;
import com.space.cornerstone.framework.core.util.JacksonUtil;
import com.space.cornerstone.system.service.TokenService;
import com.space.cornerstone.framework.core.util.ServletUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 自定义退出处理类 返回成功
 *
 */
@Component
@RequiredArgsConstructor
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private final TokenService tokenService;
    private final SysLoginLogService sysLoginLogService;

    @Value("${id.workerId:1}")
    private Long workerId;

    @Value("${id.datacenterId:1}")
    private Long datacenterId;
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

            SysLoginLog sysLoginLog = new SysLoginLog();

            String traceId = loginUser.getTraceId();
            if (StrUtil.isEmpty(traceId)) {
                traceId = IdUtil.createSnowflake(workerId, datacenterId).nextIdStr();
            }

            String ipAddr = IpUtil.getIpAddr(request);
            String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
            UserAgent agent = UserAgentUtil.parse(userAgent);

            sysLoginLog.setIp(ipAddr)
                    .setToken(loginUser.getToken())
                    .setUsername(userName)
                    .setCode(CommonEnum.SUCCESS.getCode())
                    .setSuccess(Boolean.TRUE)
                    .setUpdateTime(LocalDateTime.now())
                    .setCreateTime(LocalDateTime.now())
                    .setTraceId(traceId)
                    .setType(Constant.LOGOUT_TYPE);
            sysLoginLog.setBrowserName(agent.getBrowser().getName())
                    .setBrowserVersion(agent.getVersion())
                    .setEngineName(agent.getEngine().getName())
                    .setEngineVersion(agent.getEngineVersion())
                    .setOsName(agent.getOs().getName())
                    .setPlatformName(agent.getPlatform().getName())
                    .setMobile(agent.isMobile());
            sysLoginLogService.asyncSaveLog(sysLoginLog);
        }

        ServletUtil.returnString(response, JacksonUtil.toJson(ReturnModel.message(HttpStatus.OK.value(), "退出成功")));
    }
}
