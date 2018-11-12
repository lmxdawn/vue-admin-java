package com.lmxdawn.admin.controller.auth;

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
import java.util.HashMap;
import java.util.Map;

/**
 * 登录相关
 */
@RestController
@RequestMapping("/auth/login")
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
        String token = JwtUtil.createToken(claims, 86400L);

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
    @GetMapping("/userInfo")
    public ResultVO userInfo(HttpServletRequest request) {
        Long id = Long.valueOf(request.getHeader("X-Adminid"));
        String token = request.getHeader("X-Token");
        System.out.println(id);
        System.out.println(token);
        return null;
    }

}
