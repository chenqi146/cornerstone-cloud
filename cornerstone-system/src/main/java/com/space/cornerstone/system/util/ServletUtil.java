package com.space.cornerstone.system.util;

import cn.hutool.core.util.StrUtil;
import com.space.cornerstone.framework.core.constant.Constant;
import com.space.cornerstone.framework.core.domain.model.ReturnModel;
import com.space.cornerstone.framework.core.exception.CommonEnum;
import com.space.cornerstone.framework.core.exception.UtilException;
import com.space.cornerstone.framework.core.util.JacksonUtil;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName ServletUtil.java
 * @Description ServletUtil
 * @createTime 2021年05月23日 09:50:00
 */
public final class ServletUtil {

    public static String getToken(HttpServletRequest request) {

        String token = request.getHeader(Constant.TOKEN);

        if (StrUtil.isEmpty(token)) {
            return token;
        }

        if (!StrUtil.startWith(token, Constant.TOKEN_PREFIX)) {
            return token;
        }

        return StrUtil.removePrefix(token, Constant.TOKEN_PREFIX);
    }

    public static void returnString(HttpServletResponse response, String msg) {
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control","no-cache");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(JacksonUtil.toJson(msg));
            response.getWriter().flush();
        } catch (IOException e) {
            throw new UtilException("设置返回信息异常", e);
        }
    }

}
