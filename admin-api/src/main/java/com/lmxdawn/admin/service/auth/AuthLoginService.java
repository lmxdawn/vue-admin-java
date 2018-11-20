package com.lmxdawn.admin.service.auth;

import com.lmxdawn.admin.dto.admin.auth.LoginUserInfoDTO;
import com.lmxdawn.admin.form.admin.auth.LoginForm;
import com.lmxdawn.admin.form.admin.auth.UpdatePasswordForm;

import java.util.Map;

public interface AuthLoginService {


    Map<String, Object> loginToken(LoginForm loginForm);

    LoginUserInfoDTO findByAdminId(Long adminId);

    boolean updatePassword(UpdatePasswordForm updatePasswordForm);

}
