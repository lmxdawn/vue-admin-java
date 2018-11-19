package com.lmxdawn.admin.aspect;

import com.lmxdawn.admin.annotation.AdminAuthRuleAnnotation;
import com.lmxdawn.admin.enums.ResultEnum;
import com.lmxdawn.admin.exception.JsonException;
import com.lmxdawn.admin.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 登录验证 AOP
 */
@Aspect
@Component
@Slf4j
public class AdminAuthorizeAspect {
    @Pointcut("@annotation(com.lmxdawn.admin.annotation.AdminAuthRuleAnnotation)")
    public void adminLoginVerify() {
    }

    /**
     * 登录验证
     * @param joinPoint
     */
    @Before("adminLoginVerify()")
    public void doAdminAuthVerify(JoinPoint joinPoint) {

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

        // 判断是否进行权限验证
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //从切面中获取当前方法
        Method method = signature.getMethod();
        //得到了方,提取出他的注解
        AdminAuthRuleAnnotation action = method.getAnnotation(AdminAuthRuleAnnotation.class);
        // 如果不为空则进行权限验证
        if (!action.value().equals("")) {
            System.out.println(action.value());
            authRuleVerify(action.value());
        }

    }

    /**
     * 权限验证
     * @param authRule
     */
    private void authRuleVerify(String authRule) {
        throw new JsonException(ResultEnum.AUTH_FAILED);
    }

}
