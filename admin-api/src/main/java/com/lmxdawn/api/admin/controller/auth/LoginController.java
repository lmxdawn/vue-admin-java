package com.lmxdawn.api.admin.controller.auth;

import com.lmxdawn.api.admin.annotation.AuthRuleAnnotation;
import com.lmxdawn.api.admin.enums.ResultEnum;
import com.lmxdawn.api.admin.form.auth.LoginForm;
import com.lmxdawn.api.admin.form.auth.UpdatePasswordForm;
import com.lmxdawn.api.admin.service.auth.AuthLoginService;
import com.lmxdawn.api.common.utils.ResultVOUtils;
import com.lmxdawn.api.admin.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 登录相关
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private AuthLoginService authLoginService;

    /**
     * 用户登录
     * @return
     */
    @PostMapping(value = "/admin/auth/login/index")
    public ResultVO index(@RequestBody @Valid LoginForm loginForm,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultVOUtils.success(authLoginService.loginToken(loginForm));
    }

    /**
     * 获取登录用户信息
     * @return
     */
    @AuthRuleAnnotation("")
    @GetMapping("/admin/auth/login/userInfo")
    public ResultVO userInfo(HttpServletRequest request) {
        String adminId = request.getHeader("X-Adminid");
        Long id = Long.valueOf(adminId);

        return ResultVOUtils.success(authLoginService.findByAdminId(id));
    }

    /**
     * 登出
     * @return
     */
    @AuthRuleAnnotation("")
    @PostMapping("/admin/auth/login/out")
    public ResultVO out(){
        return ResultVOUtils.success();
    }

    /**
     * 修改密码
     * @return
     */
    @AuthRuleAnnotation("")
    @PostMapping("/admin/auth/login/password")
    public ResultVO password(@RequestBody @Valid UpdatePasswordForm updatePasswordForm,
                             BindingResult bindingResult) {
        System.out.println(updatePasswordForm);
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        boolean b = authLoginService.updatePassword(updatePasswordForm);

        if (b) {
            return ResultVOUtils.success();
        }

        return ResultVOUtils.error(ResultEnum.DATA_CHANGE);
    }

}
