package com.space.cornerstone.framework.core.aspectj;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONObject;
import com.space.cornerstone.framework.core.annotation.Log;
import com.space.cornerstone.framework.core.annotation.LogModule;
import com.space.cornerstone.framework.core.auth.Auth;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.domain.entity.system.SysOperationLog;
import com.space.cornerstone.framework.core.domain.model.AuthUser;
import com.space.cornerstone.framework.core.exception.BaseException;
import com.space.cornerstone.framework.core.util.IpUtil;
import com.space.cornerstone.framework.core.util.JacksonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName BaseLogAop.java
 * @Description BaseLogAop
 * @createTime 2021年05月30日 12:56:00
 */
public abstract class BaseLogAop {

    private static final Logger log = LoggerFactory.getLogger(BaseLogAop.class);

    /**
     * 零
     */
    private static final int ZERO = 0;
    /**
     * 截取字符串的最多长度
     */
    private static final int MAX_LENGTH = 2000;

    // 配置织入点
    @Pointcut("@annotation(com.space.cornerstone.framework.core.annotation.Log)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        SysOperationLog operationLog = new SysOperationLog();

        try {
            // 获取当前的HttpServletRequest对象
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            // 获取请求类名和方法名称
            Signature signature = joinPoint.getSignature();

            // 获取真实的方法对象
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();

            // 判断控制器方法参数中是否有RequestBody注解
            Annotation[][] annotations = method.getParameterAnnotations();
            boolean isRequestBody = isRequestBody(annotations);

            Object paramObject;
            if (isRequestBody) {
                // POST,application/json,RequestBody的类型,简单判断,然后序列化成JSON字符串
                Object[] args = joinPoint.getArgs();
                paramObject = getArgsObject(args);
            } else {
                // 获取getParameterMap中所有的值,处理后序列化成JSON字符串
                Map<String, String[]> paramsMap = request.getParameterMap();
                paramObject = getParamJSONObject(paramsMap);
            }
            String ipAddr = IpUtil.getIpAddr(request);
            String path = request.getRequestURI();
            log.info("request ===> ip=[{}], url=[{}], param=[{}]", ipAddr, path, paramObject);


            Log annotation = method.getAnnotation(Log.class);
            if (annotation == null) {
                return joinPoint.proceed();
            }

            AuthUser user = Auth.getUser();
            if (user == null) {
                return joinPoint.proceed();
            }
            // TODO: 2021-05-31 登录请求日志
            int authType = annotation.authType();


            LogModule logModule = method.getAnnotation(LogModule.class);
            if (logModule != null) {
                operationLog.setModule(StrUtil.isEmpty(logModule.name()) ? logModule.value() : logModule.name());
            }

            String traceId = request.getHeader(Constant.TRACE_ID);
            String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);

            String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
            UserAgent agent = UserAgentUtil.parse(userAgent);

            String param = StrUtil.sub(JacksonUtil.toJson(paramObject), ZERO, MAX_LENGTH);
            operationLog.setPath(path)
                    .setParam(param)
                    .setName(annotation.name())
                    .setToken(user.getToken())
                    .setIp(ipAddr)
                    .setRequestMethod(request.getMethod())
                    .setContentType(contentType)
                    .setTraceId(traceId)
                    .setUserId(user.getUser().getId())
                    .setUserName(user.getUsername());

            operationLog.setBrowserName(agent.getBrowser().getName())
                    .setBrowserVersion(agent.getVersion())
                    .setEngineName(agent.getEngine().getName())
                    .setEngineVersion(agent.getEngineVersion())
                    .setOsName(agent.getOs().getName())
                    .setPlatformName(agent.getPlatform().getName())
                    .setMobile(agent.isMobile());

            setDeviceInfo(operationLog, userAgent);

            Class<?> declaringClass = method.getDeclaringClass();
            operationLog.setClassName(declaringClass.getName())
                    .setRequestBody(isRequestBody)
                    .setMethodName(method.getName());


        } catch (Exception e) {
            log.error("aop处理日志异常: error: ", e);
        }

        try {
            Object proceed = joinPoint.proceed();
            operationLog.setMessage(JacksonUtil.toJson(proceed));
            return proceed;
        } catch (Throwable throwable) {

            String throwableMessage = throwable.getMessage();
            throwableMessage = StrUtil.sub(throwableMessage, ZERO, MAX_LENGTH);
            if (throwable instanceof BaseException) {
                BaseException baseException = (BaseException) throwable;
                operationLog.setCode(baseException.getErrorCode()).setSuccess(Boolean.FALSE);
            }
            operationLog.setExceptionMessage(throwableMessage).setExceptionName(throwable.getClass().getName());
            // TODO: 2021-05-31 异步入库
            throw throwable;
        }

    }


    /**
     * 获取参数Map的JSON字符串
     *
     * @param paramsMap
     * @return
     */
    protected Map<String, Object> getParamJSONObject(Map<String, String[]> paramsMap) {
        if (MapUtil.isEmpty(paramsMap)) {
            return new JSONObject();
        }

        Map<String, Object> map = CollUtil.newHashMap();
        for (Map.Entry<String, String[]> kv : paramsMap.entrySet()) {
            String key = kv.getKey();
            String[] values = kv.getValue();
            // 没有值
            if (values == null) {
                map.put(key, null);
            } else if (values.length == 1) {
                // 一个值
                map.put(key, values[0]);
            } else {
                // 多个值
                map.put(key, values);
            }
        }
        return map;
    }

    /**
     * 请求参数拼装
     *
     * @param args
     * @return
     */
    protected Object getArgsObject(Object[] args) {
        if (args == null) {
            return null;
        }
        // 去掉HttpServletRequest和HttpServletResponse
        List<Object> realArgs = new ArrayList<>();
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                continue;
            }
            if (arg instanceof HttpServletResponse) {
                continue;
            }
            if (arg instanceof MultipartFile) {
                continue;
            }
            if (arg instanceof ModelAndView) {
                continue;
            }
            realArgs.add(arg);
        }
        if (realArgs.size() == 1) {
            return realArgs.get(0);
        } else {
            return realArgs;
        }
    }


    private static final Pattern DEVICE_INFO_PATTERN = Pattern.compile(";\\s?(\\S*?\\s?\\S*?)\\s?Build/(\\S*?)[;)]");
    private static final Pattern DEVICE_INFO_PATTERN_1 = Pattern.compile(";\\s?(\\S*?\\s?\\S*?)\\s?\\)");

    /**
     * 获取移动端用户设备的名称和机型
     *
     * @param userAgentString
     * @return
     */
    public void setDeviceInfo(SysOperationLog operationLog, String userAgentString) {
        try {

            Matcher matcher = DEVICE_INFO_PATTERN.matcher(userAgentString);
            String model = null;
            String name = null;

            if (matcher.find()) {
                model = matcher.group(1);
                name = matcher.group(2);
            }

            if (model == null && name == null) {
                matcher = DEVICE_INFO_PATTERN_1.matcher(userAgentString);
                if (matcher.find()) {
                    model = matcher.group(1);
                }
            }

            operationLog.setDeviceName(name);
            operationLog.setDeviceModel(model);
        } catch (Exception e) {
            log.warn("获取设备信息异常, log: {}, error: ", JacksonUtil.toJson(operationLog), e);
        }
    }


    /**
     * 判断控制器方法参数中是否有RequestBody注解
     *
     * @param annotations
     * @return
     */
    protected boolean isRequestBody(Annotation[][] annotations) {
        boolean isRequestBody = false;
        for (Annotation[] annotationArray : annotations) {
            for (Annotation annotation : annotationArray) {
                if (annotation instanceof RequestBody) {
                    isRequestBody = true;
                }
            }
        }
        return isRequestBody;
    }

}
