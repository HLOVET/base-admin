package com.yaraculture.resource.config.security;

import com.yaraculture.resource.common.pojo.Result;
import com.yaraculture.resource.util.*;
import com.yaraculture.resource.util.ApiSecurityUtil;
import com.yaraculture.resource.util.ErrorUtil;
import com.yaraculture.resource.util.JsonUtil;
import com.yaraculture.resource.util.SysSettingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录失败处理
 */
@Component
@Slf4j
public class LoginFailureHandlerConfig implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        String msg = "{\"code\":\"400\",\"msg\":\"用户名或密码错误\"}";

        //判断api加密开关是否开启
        if("Y".equals(SysSettingUtil.getSysSetting().getSysApiEncrypt())){
            //加密
            try {
                //api加密
                Result encrypt = ApiSecurityUtil.encrypt(msg);

                msg = JsonUtil.stringify(encrypt);
            } catch (Throwable ee) {
                //输出到日志文件中
                log.error(ErrorUtil.errorInfoToString(ee));
            }
        }

        //转json字符串并转成Object对象，设置到Result中并赋值给返回值o
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.print(msg);
        out.flush();
        out.close();
        httpServletResponse.flushBuffer();
    }
}
