package com.lmxdawn.admin.aspect;

import com.lmxdawn.admin.enums.ResultEnum;
import com.lmxdawn.admin.exception.JsonException;
import com.lmxdawn.admin.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录验证 AOP
 */
@Aspect
@Component
@Slf4j
public class AdminAuthorizeAspect {
    @Pointcut("execution(public * com.lmxdawn.admin.controller.*.Auth*.*(..))" +
            "&& !execution(public * com.lmxdawn.admin.controller.admin.AuthLoginController.index(..))")
    public void verify() {}

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new JsonException(ResultEnum.NOT_NETWORK);
        }
        HttpServletRequest request = attributes.getRequest();

        String id = request.getHeader("X-Adminid");
        String token = request.getHeader("X-Token");
        if (token == null) {
            throw new JsonException(ResultEnum.LOGIN_VERIFY_FALL);
        }

        // 验证 token
        Claims claims = JwtUtil.parse(token);
        if (claims == null || !id.equals(String.valueOf(claims.get("admin_id")))) {
            throw new JsonException(ResultEnum.LOGIN_VERIFY_FALL);
        }


    }
}
