package com.lmxdawn.admin.controller.admin.auth;

import com.lmxdawn.admin.annotation.AdminAuthRuleAnnotation;
import com.lmxdawn.admin.entity.AuthAdmin;
import com.lmxdawn.admin.enums.ResultEnum;
import com.lmxdawn.admin.form.LoginForm;
import com.lmxdawn.admin.service.AuthAdminService;
import com.lmxdawn.admin.utils.JwtUtil;
import com.lmxdawn.admin.utils.PasswordUtil;
import com.lmxdawn.admin.utils.ResultVOUtil;
import com.lmxdawn.admin.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录相关
 */
@RestController
@RequestMapping("/admin/auth/login")
@Slf4j
public class LoginController {

    @Autowired
    private AuthAdminService authAdminService;

    /**
     * 用户登录
     * @return
     */
    @PostMapping(value = "/index")
    public ResultVO index(@Valid LoginForm loginForm,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.error(ResultEnum.PARAM_VERIFY_FALL.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        AuthAdmin authAdmin = authAdminService.findByUserName(loginForm.getUsername());
        if (authAdmin == null) {
            return ResultVOUtil.error(ResultEnum.DATA_NOT, "用户名或密码错误");
        }

        if (!PasswordUtil.authAdminPwd(loginForm.getPwd()).equals(authAdmin.getPassword())) {
            return ResultVOUtil.error(ResultEnum.DATA_NOT, "用户名或密码错误");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("admin_id", authAdmin.getId());
        String token = JwtUtil.createToken(claims, 86400L); // 一天后过期

        log.info("用户信息: " + authAdmin.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("id", authAdmin.getId());
        map.put("token", token);
        return ResultVOUtil.success(map);
    }

    /**
     * 获取登录用户信息
     * @return
     */
    @AdminAuthRuleAnnotation("admin/auth/login/userInfo")
    @GetMapping("/userInfo")
    public ResultVO userInfo(String[] str, HttpServletRequest request) {
        String adminId = request.getHeader("X-Adminid");
        Long id = Long.valueOf(adminId);
        System.out.println(Arrays.toString(str));
        AuthAdmin authAdmin = authAdminService.findById(id);


        return null;
    }

    /**
     * 登出
     * @return
     */
    public ResultVO out(){
        return null;
    }

    /**
     * 修改密码
     * @return
     */
    public ResultVO password() {
        return null;
    }

}
