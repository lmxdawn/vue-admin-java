package com.lmxdawn.api.admin.aspect;

import com.lmxdawn.api.admin.annotation.AuthRuleAnnotation;
import com.lmxdawn.api.common.enums.ResultEnum;
import com.lmxdawn.api.admin.exception.JsonException;
import com.lmxdawn.api.admin.service.auth.AuthLoginService;
import com.lmxdawn.api.admin.util.JwtUtils;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 登录验证 AOP
 */
@Aspect
@Component
@Slf4j
public class AuthorizeAspect {

    @Resource
    private AuthLoginService authLoginService;

    @Pointcut("@annotation(com.lmxdawn.api.admin.annotation.AuthRuleAnnotation)")
    public void adminLoginVerify() {
    }

    /**
     * 登录验证
     *
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
        Long adminId = null;
        try {
            adminId = Long.valueOf(id);
        }catch (Exception e) {
            throw new JsonException(ResultEnum.LOGIN_VERIFY_FALL);
        }

        String token = request.getHeader("X-Token");
        if (token == null) {
            throw new JsonException(ResultEnum.LOGIN_VERIFY_FALL);
        }

        // 验证 token
        Claims claims = JwtUtils.parse(token);
        if (claims == null) {
            throw new JsonException(ResultEnum.LOGIN_VERIFY_FALL);
        }
        Long jwtAdminId = Long.valueOf(claims.get("admin_id").toString());
        if (adminId.compareTo(jwtAdminId) != 0) {
            throw new JsonException(ResultEnum.LOGIN_VERIFY_FALL);
        }

        // 判断是否进行权限验证
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //从切面中获取当前方法
        Method method = signature.getMethod();
        //得到了方,提取出他的注解
        AuthRuleAnnotation action = method.getAnnotation(AuthRuleAnnotation.class);
        // 进行权限验证
        authRuleVerify(action.value(), adminId);
    }

    /**
     * 权限验证
     *
     * @param authRule
     */
    private void authRuleVerify(String authRule, Long adminId) {

        if (authRule != null && authRule.length() > 0) {

            List<String> authRules = authLoginService.listRuleByAdminId(adminId);
            // admin 为最高权限
            for (String item : authRules) {
                if (item.equals("admin") || item.equals(authRule)) {
                    return;
                }
            }
            throw new JsonException(ResultEnum.AUTH_FAILED);
        }

    }

}
